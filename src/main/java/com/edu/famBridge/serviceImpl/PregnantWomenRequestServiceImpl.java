package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.MarriedCoupleRequestDTO;
import com.edu.famBridge.dto.PregnantWomenRequestDTO;
import com.edu.famBridge.entity.MarriedCouple;
import com.edu.famBridge.entity.MarriedCoupleRequest;
import com.edu.famBridge.entity.PregnantWomenRequest;
import com.edu.famBridge.repository.MarriedCoupleRepository;
import com.edu.famBridge.repository.PregnantWomenRequestRepository;
import com.edu.famBridge.service.PregnantWomenRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PregnantWomenRequestServiceImpl implements PregnantWomenRequestService {


    @Autowired
    private MarriedCoupleRepository marriedCoupleRepository;

        @Autowired
        private final PregnantWomenRequestRepository pregnantWomenRequestRepository;
        @Autowired
        private ModelMapper modelMapper;

    // Get user request by id
    @Override
    public PregnantWomenRequestDTO getPregnantWomenRequestByUniqueKey(long requestId){
        PregnantWomenRequestDTO pregnantWomenRequestDTO = new PregnantWomenRequestDTO();

        try{
            PregnantWomenRequest pregnantWomenRequestById = (PregnantWomenRequest) pregnantWomenRequestRepository.findByRequestId(requestId)
                    .orElseThrow(() -> new RuntimeException("Request not found"));

            pregnantWomenRequestDTO.setEmail(pregnantWomenRequestById.getEmail());
            pregnantWomenRequestDTO.setFirstName(pregnantWomenRequestById.getFirstName());
            pregnantWomenRequestDTO.setRequestDate(pregnantWomenRequestById.getRequestDate());
            pregnantWomenRequestDTO.setRequestId(pregnantWomenRequestById.getRequestId());
            pregnantWomenRequestDTO.setGramaNiladhariDvision((pregnantWomenRequestById.getGramaNiladhariDivision()));
            pregnantWomenRequestDTO.setAppointmentDate((pregnantWomenRequestById.getAppointmentDate()));
            pregnantWomenRequestDTO.setStatusCode(200);
            pregnantWomenRequestDTO.setMessage("Married couple request for Id: " + requestId + " found successfully.");


        }

        catch (Exception e){
            pregnantWomenRequestDTO.setStatusCode(500);
            pregnantWomenRequestDTO.setMessage("Error occurred: " + e.getMessage());
        }

        return pregnantWomenRequestDTO;
    }


        @Override
        public PregnantWomenRequestDTO savePregnantWomenRequest(PregnantWomenRequestDTO pregnantWomenRequestDTO) {
            PregnantWomenRequestDTO response = new PregnantWomenRequestDTO();
            String email = pregnantWomenRequestDTO.getEmail();

            try {
                Optional<MarriedCouple> marriedCoupleOptional = marriedCoupleRepository.findByEmail(email);

                if (marriedCoupleOptional.isPresent()) {
                    MarriedCouple marriedCouple = marriedCoupleOptional.get();
                    String nic = marriedCouple.getMarriedCoupleRequest().getNic();

                    PregnantWomenRequest pregnantWomenRequest = new PregnantWomenRequest();
                    pregnantWomenRequest.setEmail(marriedCouple.getEmail());
                    pregnantWomenRequest.setFirstName(marriedCouple.getFirstName());
                    pregnantWomenRequest.setContactNo(marriedCouple.getContactNo());
                    pregnantWomenRequest.setAssignMidwife(marriedCouple.getAssignMidwife());
                    pregnantWomenRequest.setGramaNiladhariDivision(marriedCouple.getGramaNiladhariDivision());
                    pregnantWomenRequest.setNic(nic);
                    pregnantWomenRequest.setPhmArea(marriedCouple.getPhmArea());
                    pregnantWomenRequest.setRequestDate(LocalDate.now());
                    pregnantWomenRequest.setStatus("Pending");
                    pregnantWomenRequest.setAppointmentDate(pregnantWomenRequest.getAppointmentDate());
                    pregnantWomenRequest.setPregnancyProof(pregnantWomenRequestDTO.getPregnancyProof());
                    pregnantWomenRequest.setMarriedCouple(marriedCouple);

                    pregnantWomenRequestRepository.save(pregnantWomenRequest);

                    response.setStatusCode(200);
                    response.setMessage("Request Sent Successfully.");
                } else {
                    response.setStatusCode(404);
                    response.setMessage("Request not match with Registered ID");
                }

            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage("Error saving the request: " + e.getMessage());
            }

            return response;
        }

        @Override
        public List<String> getPregnantWomenRequestDetails(String phmArea){
            List<Object[]> results = pregnantWomenRequestRepository.findRequestDetailsByPhmArea(phmArea);

            List<String> responseList = results.stream()
                    .map(row -> String.join(", ",
                            row[0].toString(),  //requestId
                            row[1].toString(),  // first_name
                            row[2].toString(),  // email
                            row[3].toString(),  // nic
                            row[4].toString(),  // phmArea
                            row[5].toString(),  // assignMidwife
                            row[6].toString(),  // contactNo
                            row[7].toString(),  // request_date
                            row[8].toString()   //requestStatus
                    ))
                    .collect(Collectors.toList());

            return responseList;
        }

        @Override
        public byte[] getPregnancyProofByRequestId(Long requestId){
            PregnantWomenRequest request = pregnantWomenRequestRepository.findById(requestId)
                    .orElseThrow(() -> new RuntimeException("Request not found with ID: " + requestId));

            return request.getPregnancyProof();
        }

    }


