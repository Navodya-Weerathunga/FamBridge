package com.edu.famBridge.service;

import com.edu.famBridge.dto.PregnantWomenDTO;

import java.util.List;

public interface PregnantWomenService {
    PregnantWomenDTO savePregnantWomen(Long requestId, PregnantWomenDTO pregnantWomenDTO);

    List<PregnantWomenDTO> getAllPregnantWomen(String phmArea);

    List<Integer> getPregnancyCards(String email);
}
