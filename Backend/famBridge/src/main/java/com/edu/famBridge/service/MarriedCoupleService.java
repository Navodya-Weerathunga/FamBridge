package com.edu.famBridge.service;

import com.edu.famBridge.dto.MarriedCoupleDTO;
import com.edu.famBridge.dto.MarriedCoupleUpdateDTO;

import java.util.List;

public interface MarriedCoupleService {
    MarriedCoupleDTO saveMarriedCouple(MarriedCoupleDTO marriedCoupleDTO);

    List<MarriedCoupleDTO> getAllMarriedCouples(String phmArea);

    MarriedCoupleDTO loginMarriedCouple(MarriedCoupleDTO marriedCoupleDTO);

    MarriedCoupleDTO updatePassword(MarriedCoupleDTO marriedCoupleDTO);

    MarriedCoupleDTO getMarriedCoupleDetailsById(Long marriedCoupleId);

    MarriedCoupleDTO  getMarriedCoupleDetailsByEmail(String email);

    MarriedCoupleUpdateDTO updateProfile(String email, MarriedCoupleUpdateDTO marriedCoupleUpdateDTO);
}
