package com.edu.famBridge.service;

import com.edu.famBridge.dto.MidwifeDTO;
import com.edu.famBridge.dto.MidwifeUpdateDTO;
import com.edu.famBridge.entity.Midwife;

import java.util.List;

public interface MidwifeService {
    List<Midwife> getAllMidwifesToList();
    List<Midwife> getAllMidwifes();
    MidwifeDTO getMidwifeByWorkEmail(String email);
    MidwifeDTO getMidwifeById(String midwifeId );



        MidwifeDTO saveMidwife(MidwifeDTO midwifeDto);

        void sendLoginDetails(String email, String password);

        MidwifeDTO loginMidwife(MidwifeDTO midwifeDTO);

        MidwifeDTO getAllMidwives();

        MidwifeDTO getMidwifeByEmail(String email);

        MidwifeDTO updatePassword(MidwifeDTO midwifeDTO);

        List<String> findMidwifeByWorkingArea(String workingArea);

        MidwifeUpdateDTO updateProfile(String email, MidwifeUpdateDTO midwifeUpdateDTO);
    }


