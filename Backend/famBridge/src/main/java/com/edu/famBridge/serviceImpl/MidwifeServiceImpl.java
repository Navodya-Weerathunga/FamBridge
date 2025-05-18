package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.MidwifeDTO;
import com.edu.famBridge.dto.MidwifeUpdateDTO;
import com.edu.famBridge.entity.Midwife;
import com.edu.famBridge.repository.MidwifeRepository;
import com.edu.famBridge.service.MidwifeService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MidwifeServiceImpl implements MidwifeService {

    @Autowired
    private MidwifeRepository midwifeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public MidwifeDTO saveMidwife(MidwifeDTO midwifeDto) {
        
        MidwifeDTO response = new MidwifeDTO();

        Optional<Midwife> midwifeOptional = midwifeRepository.findByNic(midwifeDto.getNic());
        try {
            if (midwifeOptional.isEmpty()) {
                String plainPassword = midwifeDto.getPassword();
                midwifeDto.setPassword(passwordEncoder.encode(plainPassword));
                Midwife newMidwife = modelMapper.map(midwifeDto, Midwife.class);
                midwifeRepository.save(newMidwife);
                response.setStatusCode(200);
                response.setMessage("Midwife save successfully.");
                sendLoginDetails(midwifeDto.getEmail(), plainPassword);
            } else {
                response.setMessage("Midwife Already Exists!");

            }

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;

    }

    @Override
    public void sendLoginDetails(String email, String password){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Login Details");
            mimeMessageHelper.setText("You are successfully registered to FamBridge; Midwife Procedure Management System. Use below login credentials to login to the system.  \nUser Name : " + email +" \n Password : " + password );
            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e){
            throw new RuntimeException("Unable to send login details");
        }
    }

    @Override
    public MidwifeDTO loginMidwife(MidwifeDTO midwifeDTO){
        MidwifeDTO response = new MidwifeDTO();

        Optional<Midwife> optionalMidwife = midwifeRepository.findByEmail(midwifeDTO.getEmail());

        if (!optionalMidwife.isPresent()) {
            response.setStatusCode(404);
            response.setMessage("Email not found");
            response.setLogin(false);
            return response;
        }

        Midwife midwife = optionalMidwife.get();
        boolean isPasswordRight = passwordEncoder.matches(midwifeDTO.getPassword(), midwife.getPassword());

        if (isPasswordRight) {
            response.setStatusCode(200);
            response.setEmail(midwife.getEmail());
            response.setMidwifeType(midwife.getMidwifeType());
            response.setWorkingArea(midwife.getWorkingArea());
            response.setMohArea(midwife.getMohArea());
            response.setMessage("Login Success");
            response.setLogin(true);
        } else {
            response.setStatusCode(401);
            response.setMessage("Invalid password");
            response.setLogin(false);
        }

        return response;

    }

    @Override
    public MidwifeDTO getAllMidwives(){
        MidwifeDTO midwifeDto = new MidwifeDTO();

        try{
            List<Midwife> result = midwifeRepository.findAll();
            if(!result.isEmpty()){
                midwifeDto.setMidwifeList(result);
                midwifeDto.setStatusCode(200);
                midwifeDto.setMessage("Successful");
            }
            else{
                midwifeDto.setStatusCode(404);
                midwifeDto.setMessage("No midwife found");
            }
            return midwifeDto;
        }

        catch (Exception e){
            midwifeDto.setStatusCode(500);
            midwifeDto.setMessage("Error occurred: " + e.getMessage());
            return midwifeDto;
        }
    }

    @Override
    public MidwifeDTO getMidwifeByEmail(String email){
        MidwifeDTO midwifeDTO = new MidwifeDTO();

        try{
            Midwife midwifeByEmail = midwifeRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Midwife not found"));
            midwifeDTO.setMidwife(midwifeByEmail);
            midwifeDTO.setStatusCode(200);
            midwifeDTO.setMessage("Midwife with Email: " + email + " found successfully.");
        }

        catch (Exception e){
            midwifeDTO.setStatusCode(500);
            midwifeDTO.setMessage("Error occurred: " + e.getMessage());
        }

        return midwifeDTO;
    }

    @Override
    public MidwifeDTO updatePassword(MidwifeDTO midwifeDTO){
        MidwifeDTO response = new MidwifeDTO();

        Optional<Midwife> midwifeOptional = midwifeRepository.findByEmail(midwifeDTO.getEmail());

        if(midwifeOptional.isPresent()) {
            Midwife midwife = midwifeOptional.get();
            String confirmPassword = midwifeDTO.getConfirmPassword();
            String newPassword = midwifeDTO.getPassword();

            boolean isPasswordMatch = newPassword.equals(confirmPassword);

            if (isPasswordMatch) {
                midwife.setPassword(passwordEncoder.encode(newPassword));
                midwifeRepository.save(midwife);
                response.setStatusCode(200);
                response.setMessage("Password Updated Successfully");
            } else {
                response.setStatusCode(400);
                response.setMessage("Password Not Match with the Confirm Password");
            }

        }

        else{
            response.setMessage("Midwife Not Found");
        }
        return response;
    }

    public List<String> findMidwifeByWorkingArea(String workingArea){
        return midwifeRepository.findFullNameByArea(workingArea);
    }

    @Transactional
    @Override
    public MidwifeUpdateDTO updateProfile(String email, MidwifeUpdateDTO midwifeDTO){

       Optional<Midwife>midwifeOptional = midwifeRepository.findByEmail(email);

       if(midwifeOptional.isPresent()){
           Midwife midwife = midwifeOptional.get();

           if(midwifeDTO.getNic() != null) midwife.setNic(midwifeDTO.getNic());
           if(midwifeDTO.getEmail() != null) midwife.setEmail(midwifeDTO.getEmail());
           if(midwifeDTO.getDob() != null) midwife.setDob(midwifeDTO.getDob());
           if(midwifeDTO.getAddress() != null) midwife.setAddress(midwifeDTO.getAddress());
           if(midwifeDTO.getContactNumber() != null) midwife.setContactNumber(midwifeDTO.getContactNumber());
           if(midwifeDTO.getMedicalCouncilNumber() != null) midwife.setMedicalCouncilNumber(midwifeDTO.getMedicalCouncilNumber());

           midwifeDTO.setMessage("Details Updated Successfully");

           midwifeRepository.save(midwife);
       }

       else{
           midwifeDTO.setMessage("Not Found");
       }

       return midwifeDTO;
    }

}
