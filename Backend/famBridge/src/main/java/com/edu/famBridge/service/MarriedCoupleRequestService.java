package com.edu.famBridge.service;

import com.edu.famBridge.dto.MarriedCoupleRequestDTO;

import java.util.List;

public interface MarriedCoupleRequestService {
    MarriedCoupleRequestDTO saveMarriedCoupleRequest(MarriedCoupleRequestDTO marriedCoupleRequestDTO);

    List<String> getMarriedCoupleRequestDetails(String mohArea);

    List<String> getMarriedCoupleRequestDetailsById(Long requestId);

    byte[] getMarriageProofByRequestId(Long requestId);
}
