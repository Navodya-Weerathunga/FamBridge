package com.edu.famBridge.serviceImpl;

import com.edu.famBridge.dto.MarriedCoupleRequestDTO;
import com.edu.famBridge.entity.MarriedCoupleRequest;
import com.edu.famBridge.repository.MarriedCoupleRequestRepository;
import com.edu.famBridge.service.MarriedCoupleRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarriedCoupleRequestServiceImpl implements MarriedCoupleRequestService {

    @Autowired
    private MarriedCoupleRequestRepository marriedCoupleRequestRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MarriedCoupleRequestDTO saveMarriedCoupleRequest(MarriedCoupleRequestDTO marriedCoupleRequestDTO){

        MarriedCoupleRequestDTO response = new MarriedCoupleRequestDTO();

        Optional<MarriedCoupleRequest>marriedCoupleRequestOptional = marriedCoupleRequestRepository.findByNic(marriedCoupleRequestDTO.getNic());

        try {
            if (marriedCoupleRequestOptional.isEmpty()){
                marriedCoupleRequestDTO.setRequestStatus("Pending");
                marriedCoupleRequestDTO.setRequestDate(LocalDate.from(LocalDateTime.now()));

                MarriedCoupleRequest newMarriedCoupleRequest = modelMapper.map(marriedCoupleRequestDTO, MarriedCoupleRequest.class);
                marriedCoupleRequestRepository.save(newMarriedCoupleRequest);
                response.setStatusCode(200);
                response.setMessage("Request Sent Successfully.");

            }

            else {
                response.setMessage("You have already requested");

            }
        }

        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }


    @Override
    public List<String> getMarriedCoupleRequestDetails(String mohArea) {
        List<Object[]> results = marriedCoupleRequestRepository.findRequestDetailsByMohArea(mohArea);

        List<String> responseList = results.stream()
                .map(row -> String.join(", ",
                        row[0].toString(),  //requestId
                        row[1].toString(),  // first_name
                        row[2].toString(),  // last_name
                        row[3].toString(),  // email
                        row[4].toString(),  // nic
                        row[5].toString(),  // moh_area
                        row[6].toString(),  // grama_niladhari_division
                        row[7].toString(),  // address
                        row[8].toString(),   // request_date
                        row[9].toString()    //requestSatatu
                ))
                .collect(Collectors.toList());

        return responseList;
    }

    @Override
    public List<String> getMarriedCoupleRequestDetailsById(Long requestId) {
        List<Object[]> results = marriedCoupleRequestRepository.findRequestDetailsById(requestId);

        List<String> responseList = results.stream()
                .map(row -> String.join(", ",
                        row[0].toString(),  // first_name
                        row[1].toString(),  // last_name
                        row[2].toString(),  // email
                        row[3].toString(),  // nic
                        row[4].toString(),  // moh_area
                        row[5].toString(),  // grama_niladhari_division
                        row[6].toString(),  // address
                        row[7].toString()   // request_date
                ))
                .collect(Collectors.toList());

        return responseList;
    }


    @Override
    public byte[] getMarriageProofByRequestId(Long requestId){
        MarriedCoupleRequest request = marriedCoupleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with ID: " + requestId));

        return request.getMarriageProof();
    }



}
