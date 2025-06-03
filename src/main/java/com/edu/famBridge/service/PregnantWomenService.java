package com.edu.famBridge.service;


import com.edu.famBridge.dto.PregnantWomenDTO;
import com.edu.famBridge.entity.PregnantWomen;
import jakarta.validation.Valid;

import java.util.List;

public interface PregnantWomenService {
    PregnantWomenDTO getWomenByNic(@Valid String nic);
    List<PregnantWomenDTO> getAllWomenByNic(String nic);


        PregnantWomenDTO savePregnantWomen(Long requestId, PregnantWomenDTO pregnantWomenDTO);

        List<PregnantWomenDTO> getAllPregnantWomen(String phmArea);

        List<Integer> getPregnancyCards(String email);
    }


