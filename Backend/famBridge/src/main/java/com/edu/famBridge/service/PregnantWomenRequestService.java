package com.edu.famBridge.service;

import com.edu.famBridge.dto.MarriedCoupleRequestDTO;
import com.edu.famBridge.dto.PregnantWomenRequestDTO;

import java.util.List;

public interface PregnantWomenRequestService {
    PregnantWomenRequestDTO savePregnantWomenRequest(PregnantWomenRequestDTO pregnantWomenRequestDTO);

    List<String> getPregnantWomenRequestDetails(String phmArea);

    byte[] getPregnancyProofByRequestId(Long requestId);
}
