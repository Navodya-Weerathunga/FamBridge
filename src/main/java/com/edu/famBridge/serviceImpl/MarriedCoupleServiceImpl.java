package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.MarriedCoupleDTO;
import com.edu.famBridge.dto.MarriedCoupleUpdateDTO;
import com.edu.famBridge.entity.MarriedCouple;
import com.edu.famBridge.entity.MarriedCoupleRequest;
import com.edu.famBridge.entity.PregnantWomen;
import com.edu.famBridge.entity.PregnantWomenRequest;
import com.edu.famBridge.repository.MarriedCoupleRepository;
import com.edu.famBridge.repository.MarriedCoupleRequestRepository;
import com.edu.famBridge.repository.PregnantWomenRepository;
import com.edu.famBridge.repository.PregnantWomenRequestRepository;
import com.edu.famBridge.service.MarriedCoupleService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class MarriedCoupleServiceImpl implements MarriedCoupleService {
    @Autowired
    private final MarriedCoupleRepository marriedCoupleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MarriedCoupleRequestRepository marriedCoupleRequestRepository;

    @Autowired
    private PregnantWomenRequestRepository pregnantWomenRequestRepository;

    @Autowired
    private PregnantWomenRepository pregnantWomenRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    //Get couple by nic
    @Override
    public MarriedCoupleDTO getCoupleByNic(@Valid String nic){
        MarriedCouple couple=marriedCoupleRepository.getCoupleByNic(nic);

        return modelMapper.map(couple, MarriedCoupleDTO.class);
    }

    @Override// Get all Couples
    public List<MarriedCouple> getAllCouples() {
        return marriedCoupleRepository.findAll();
    }


        @Override
        public MarriedCoupleDTO saveMarriedCouple(MarriedCoupleDTO marriedCoupleDTO) {

            MarriedCoupleDTO response = new MarriedCoupleDTO();

            try {
                Optional<MarriedCoupleRequest> marriedCoupleRequestOptional = marriedCoupleRequestRepository.
                        findByNic(marriedCoupleDTO.getNic());


                if (marriedCoupleRequestOptional.isPresent()) {

                    MarriedCoupleRequest marriedCoupleRequest = marriedCoupleRequestOptional.get();

                    marriedCoupleDTO.setFirstName(marriedCoupleRequest.getFirstName());
                    marriedCoupleDTO.setLastName(marriedCoupleRequest.getLastName());

                    String otp = generateOtp();

                    marriedCoupleDTO.setPassword(passwordEncoder.encode(otp));

                    marriedCoupleDTO.setNic(marriedCoupleRequest.getNic());

                    marriedCoupleDTO.setRegisteredDate(LocalDate.from(LocalDate.now()));

                    marriedCoupleDTO.setLoginCount(0);

                    String firstName = marriedCoupleRequest.getFirstName();
                    marriedCoupleDTO.setFirstName(firstName);

                    String email = marriedCoupleRequest.getEmail();
                    marriedCoupleDTO.setEmail(email);

                    marriedCoupleDTO.setGramaNiladhariDivision(marriedCoupleRequest.getGramaNiladhariDivision());

                    String contactNo = marriedCoupleRequest.getContactNo();
                    marriedCoupleDTO.setContactNo(contactNo);

                    marriedCoupleRequest.setRequestStatus("Registered");

                    sendLoginDetails(email, otp);

                    MarriedCouple newMarriedCouple = modelMapper.map(marriedCoupleDTO, MarriedCouple.class);
                    newMarriedCouple.setMarriedCoupleRequest(marriedCoupleRequest);
                    marriedCoupleRepository.save(newMarriedCouple);
                    response.setStatusCode(200);
                    response.setMessage("Married Couple Saved Successfully.");


                } else {
                    response.setMessage("No request found for NIC: " + marriedCoupleDTO.getNic());
                }

            }

            catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage(e.getMessage());
            }

            return response;


        }


        public void sendLoginDetails(String email, String password){
            try{
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setTo(email);
                mimeMessageHelper.setSubject("Login Details");
                mimeMessageHelper.setText("You are successfully registered to FamBridge; Midwife Procedure Management System. Use below login credentials to login to the system.  \nUser Name : " + email +" \n OTP : " + password );
                javaMailSender.send(mimeMessage);
            }
            catch (MessagingException e){
                throw new RuntimeException("Unable to send login details");
            }
        }

        private String generateOtp(){
            SecureRandom random = new SecureRandom();
            int otp = 100000 + random.nextInt(900000);
            return String.valueOf(otp);
        }

        @Override
        @Transactional(readOnly=true)
        public List<MarriedCoupleDTO> getAllMarriedCouples(String phmArea){
            List<MarriedCouple> marriedCouples = marriedCoupleRepository.findByPhmArea(phmArea);
            return modelMapper.map(marriedCouples,new TypeToken<List<MarriedCoupleDTO>>(){}.getType());

        }

        @Override
        public MarriedCoupleDTO loginMarriedCouple(MarriedCoupleDTO marriedCoupleDTO){
            MarriedCoupleDTO response = new MarriedCoupleDTO();

            Optional<MarriedCouple> optionalMarriedCouple = marriedCoupleRepository.findByEmail(marriedCoupleDTO.getEmail());

            if (optionalMarriedCouple.isEmpty()) {
                response.setStatusCode(404);
                response.setMessage("Email not found");
                response.setLogin(0);
                return response;
            }

            else {

                MarriedCouple marriedCouple = optionalMarriedCouple.get();

                String loginPassword = marriedCoupleDTO.getPassword();
                String password =  marriedCouple.getPassword();

                boolean isPasswordRight = passwordEncoder.matches(loginPassword, password);

                if(isPasswordRight){

                    if(marriedCouple.getLoginCount() > 0){
                        response.setStatusCode(200);
                        marriedCouple.setLoginCount(marriedCouple.getLoginCount() + 1);
                        marriedCoupleRepository.save(marriedCouple);
                        response.setEmail(marriedCouple.getEmail());
                        response.setNic(marriedCouple.getMarriedCoupleRequest().getNic());
                        response.setFirstName(marriedCouple.getFirstName());
                        response.setLoginCount(marriedCouple.getLoginCount());
                        response.setMarriedCoupleId(marriedCouple.getMarriedCoupleId());
                        response.setMessage("Login Success");
                        response.setLogin(1);
                    }

                    else {
                        response.setMessage("You have to update your OTP to new Password");
                        //updatePassword(marriedCoupleDTO);
                        response.setLogin(1);
                        marriedCouple.setLoginCount(1);
                        marriedCoupleRepository.save(marriedCouple);
                    }

                }

                else{
                    response.setStatusCode(401);
                    response.setMessage("Invalid password");
                    response.setLogin(0);
                }

                return response;
            }
        }

        @Override
        public MarriedCoupleDTO updatePassword(MarriedCoupleDTO marriedCoupleDTO){
            MarriedCoupleDTO response = new MarriedCoupleDTO();

            Optional<MarriedCouple> optionalMarriedCouple = marriedCoupleRepository.findByEmail(marriedCoupleDTO.getEmail());

            if(optionalMarriedCouple.isPresent()){
                MarriedCouple marriedCouple = optionalMarriedCouple.get();
                String confirmPassword = marriedCoupleDTO.getConfirmPassword();
                String newPassword = marriedCoupleDTO.getPassword();

                boolean isPasswordMatch = newPassword.equals(confirmPassword);

                if(isPasswordMatch){
                    marriedCouple.setPassword(passwordEncoder.encode(newPassword));
                    marriedCoupleRepository.save(marriedCouple);
                    response.setStatusCode(200);
                    response.setMessage("Password Updated Successfully");
                }

                else{
                    response.setStatusCode(400);
                    response.setMessage("Password Not Match with The Confirm Password");
                }
            }

            else{
                response.setStatusCode(404);
                response.setMessage("User Not Found");
            }

            return response;
        }


        @Override
        public MarriedCoupleDTO getMarriedCoupleDetailsById(Long marriedCoupleId){
            Optional<MarriedCouple> marriedCoupleOptional = marriedCoupleRepository.findByMarriedCoupleId(marriedCoupleId);
            return modelMapper.map(marriedCoupleOptional, MarriedCoupleDTO.class);
        }

        @Override
        public MarriedCoupleDTO  getMarriedCoupleDetailsByEmail(String email){
            Optional<MarriedCouple>marriedCoupleOptional = marriedCoupleRepository.findByEmail(email);
            return modelMapper.map(marriedCoupleOptional, MarriedCoupleDTO.class);
        }

        @Transactional
        @Override
        public MarriedCoupleUpdateDTO updateProfile(String email, MarriedCoupleUpdateDTO dto){
            Optional<MarriedCouple>marriedCoupleOptional = marriedCoupleRepository.findByEmail(email);

            if(marriedCoupleOptional.isPresent()){
                MarriedCouple couple = marriedCoupleOptional.get();

                if (dto.getFirstName() != null) couple.setFirstName(dto.getFirstName());
                if (dto.getLastName() != null) couple.setLastName(dto.getLastName());
                if (dto.getEmail() != null) couple.setEmail(dto.getEmail());
                if (dto.getContactNo() != null) couple.setContactNo(dto.getContactNo());
                if (dto.getOccupation() != null) couple.setOccupation(dto.getOccupation());
                if (dto.getHusbandFirstName() != null) couple.setHusbandFirstName(dto.getHusbandFirstName());
                if (dto.getHusbandLastName() != null) couple.setHusbandLastName(dto.getHusbandLastName());
                if (dto.getHusbandContactNo() != null) couple.setHusbandContactNo(dto.getHusbandContactNo());
                if (dto.getHusbandOccupation() != null) couple.setHusbandOccupation(dto.getHusbandOccupation());

                MarriedCoupleRequest coupleRequest = couple.getMarriedCoupleRequest();

                if (coupleRequest != null) {
                    if (dto.getFirstName() != null) coupleRequest.setFirstName(dto.getFirstName());
                    if (dto.getLastName() != null) coupleRequest.setLastName(dto.getLastName());
                    if (dto.getEmail() != null) coupleRequest.setEmail(dto.getEmail());
                    if (dto.getContactNo() != null) coupleRequest.setContactNo(dto.getContactNo());
                    marriedCoupleRequestRepository.save(coupleRequest);
                }

                List <PregnantWomenRequest> womenRequest = pregnantWomenRequestRepository.findByEmail(email);
                for (PregnantWomenRequest request : womenRequest) {
                    if (dto.getFirstName() != null) request.setFirstName(dto.getFirstName());
                    if (dto.getEmail() != null) request.setEmail(dto.getEmail());
                    if (dto.getContactNo() != null) request.setContactNo(dto.getContactNo());
                }

                pregnantWomenRequestRepository.saveAll(womenRequest);

                List <PregnantWomen> women = pregnantWomenRepository.findByEmail(email);
                for (PregnantWomen pregnantWomen : women) {
                    if (dto.getFirstName() != null) pregnantWomen.setFirstName(dto.getFirstName());
                    if (dto.getEmail() != null) pregnantWomen.setEmail(dto.getEmail());
                    if (dto.getContactNo() != null) pregnantWomen.setContactNo(dto.getContactNo());
                }

                pregnantWomenRepository.saveAll(women);

                dto.setMessage("User Details Updated Successfully");

                marriedCoupleRepository.save(couple);

            }

            else{
                dto.setMessage("User Not Found");
            }

            return dto;
        }
    }


