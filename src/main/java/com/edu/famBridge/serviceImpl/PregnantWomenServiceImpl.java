package com.edu.famBridge.serviceImpl;



import com.edu.famBridge.dto.PregnantWomenDTO;
import com.edu.famBridge.entity.MarriedCouple;
import com.edu.famBridge.entity.PregnantWomen;
import com.edu.famBridge.entity.PregnantWomenRequest;
import com.edu.famBridge.repository.MarriedCoupleRepository;
import com.edu.famBridge.repository.PregnantWomenRepository;
import com.edu.famBridge.repository.PregnantWomenRequestRepository;
import com.edu.famBridge.service.PregnantWomenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PregnantWomenServiceImpl implements PregnantWomenService {
    @Autowired
    private final PregnantWomenRepository pregnantWomenRepository ;

    @Autowired
    private MarriedCoupleRepository marriedCoupleRepository;

    @Autowired
    private PregnantWomenRequestRepository pregnantWomenRequestRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override // Get women by nic
    public PregnantWomenDTO getWomenByNic(@Valid String nic){
       PregnantWomen women=pregnantWomenRepository.getwomenByNic(nic);
        return modelMapper.map(women, PregnantWomenDTO.class);
    }
    //Get same Women list  by nic
    @Override
    public List<PregnantWomenDTO> getAllWomenByNic(String nic) {
        List<PregnantWomen> women = pregnantWomenRepository.findByNic(nic);
        List<PregnantWomenDTO> dtoList = new ArrayList<>();

        for (PregnantWomen woman : women) {
            PregnantWomenDTO dto = new PregnantWomenDTO();
            dto.setPregnancyRecordNo(woman.getPregnancyRecordNo());
            dto.setRegisteredDate(woman.getRegisteredDate());
            dto.setFirstName(woman.getFirstName());
            dto.setEmail(woman.getEmail());
            dto.setAssignMidwife(woman.getAssignMidwife());
            dto.setContactNo(woman.getContactNo());
            dto.setNic(woman.getNic());
            dto.setRequestId(woman.getPregnantWomenRequest().getRequestId());

            MarriedCouple mc = woman.getPregnantWomenRequest().getMarriedCouple();
            if (mc != null) {
                dto.setFirstName(mc.getFirstName());

            }

            dtoList.add(dto);
        }

        return dtoList;
    }




        @Override
        public PregnantWomenDTO savePregnantWomen(Long requestId, PregnantWomenDTO pregnantWomenDTO){
            PregnantWomenDTO response = new PregnantWomenDTO();

            Optional<PregnantWomenRequest> optional = pregnantWomenRequestRepository.findById(requestId);

            try{
                if(optional.isPresent()){
                    PregnantWomenRequest pregnantWomenRequest = optional.get();

                    Integer pregnancyRecordNo = generatePregnancyRecordNo();

                    pregnantWomenDTO.setRegisteredDate(LocalDate.now());
                    pregnantWomenDTO.setPregnancyRecordNo(pregnancyRecordNo);

                    pregnantWomenDTO.setFirstName(pregnantWomenRequest.getFirstName());
                    pregnantWomenDTO.setEmail(pregnantWomenRequest.getEmail());
                    pregnantWomenDTO.setContactNo(pregnantWomenRequest.getContactNo());
                    pregnantWomenDTO.setAssignMidwife(pregnantWomenRequest.getAssignMidwife());
                    pregnantWomenDTO.setPhmArea(pregnantWomenRequest.getPhmArea());
                    pregnantWomenDTO.setNic(pregnantWomenRequest.getNic());

                    pregnantWomenRequest.setStatus("Registered");

                    PregnantWomen newPregnantWomen = modelMapper.map(pregnantWomenDTO, PregnantWomen.class);
                    newPregnantWomen.setPregnantWomenRequest(pregnantWomenRequest);
                    pregnantWomenRepository.save(newPregnantWomen);
                    response.setStatusCode(200);
                    response.setMessage("Pregnant Woman Registered Successfully.");
                }

                else {
                    response.setStatusCode(404);
                    response.setMessage("Request Not Found");
                }
            }
            catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage(e.getMessage());
            }

            return response;

        }

        private Integer generatePregnancyRecordNo() {
            Random object = new Random();
            Integer pregnancyRecordNo;
            do pregnancyRecordNo = 100000 + object.nextInt(900000); while (pregnantWomenRepository.existsById(pregnancyRecordNo));
            return pregnancyRecordNo;
        }

        @Override
        public List<PregnantWomenDTO> getAllPregnantWomen(String phmArea){
            List<PregnantWomen> women = pregnantWomenRepository.findByPhmArea(phmArea);
            return modelMapper.map(women,new TypeToken<List<PregnantWomenDTO>>(){}.getType());
        }

        @Override
        public List<Integer> getPregnancyCards(String email) {
            return pregnantWomenRepository.findPregnancyRecordNoByEmail(email);
        }



    }




