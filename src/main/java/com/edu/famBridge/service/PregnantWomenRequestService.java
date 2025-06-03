package com.edu.famBridge.service;

import com.edu.famBridge.dto.PregnantWomenRequestDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PregnantWomenRequestService {
    PregnantWomenRequestDTO getPregnantWomenRequestByUniqueKey(long requestId);


        PregnantWomenRequestDTO savePregnantWomenRequest(PregnantWomenRequestDTO pregnantWomenRequestDTO);

        List<String> getPregnantWomenRequestDetails(String phmArea);

        byte[] getPregnancyProofByRequestId(Long requestId);
    }


