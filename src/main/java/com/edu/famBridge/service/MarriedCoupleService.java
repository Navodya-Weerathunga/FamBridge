package com.edu.famBridge.service;

import com.edu.famBridge.dto.MarriedCoupleDTO;
import com.edu.famBridge.dto.MarriedCoupleUpdateDTO;
import com.edu.famBridge.entity.MarriedCouple;
import jakarta.validation.Valid;

import java.util.List;

public interface MarriedCoupleService {
    MarriedCoupleDTO getCoupleByNic(@Valid String nic);
    List<MarriedCouple> getAllCouples();

        MarriedCoupleDTO saveMarriedCouple(MarriedCoupleDTO marriedCoupleDTO);

        List<MarriedCoupleDTO> getAllMarriedCouples(String phmArea);

        MarriedCoupleDTO loginMarriedCouple(MarriedCoupleDTO marriedCoupleDTO);

        MarriedCoupleDTO updatePassword(MarriedCoupleDTO marriedCoupleDTO);

        MarriedCoupleDTO getMarriedCoupleDetailsById(Long marriedCoupleId);

        MarriedCoupleDTO  getMarriedCoupleDetailsByEmail(String email);

        MarriedCoupleUpdateDTO updateProfile(String email, MarriedCoupleUpdateDTO marriedCoupleUpdateDTO);


}
