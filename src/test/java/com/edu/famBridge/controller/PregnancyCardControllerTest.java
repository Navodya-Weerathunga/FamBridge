package com.edu.famBridge.controller;

import com.edu.famBridge.dto.PregnancyCardDTO;
import com.edu.famBridge.serviceImpl.PregnancyCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PregnancyCardController.class)
@AutoConfigureMockMvc(addFilters = false)
class PregnancyCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PregnancyCardService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddPregnancyCard() throws Exception {
        PregnancyCardDTO dto = new PregnancyCardDTO();
        dto.setPregnancyRecordNo(101);
        dto.setCid(1L);

        when(service.addPregnancyCard(any())).thenReturn(dto);

        mockMvc.perform(post("/famBridge/pregnancyCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pregnancyRecordNo").value(101));
    }

    @Test
    void testGetAllPregnancyCards() throws Exception {
        PregnancyCardDTO dto = new PregnancyCardDTO();
        dto.setCid(1L);
        dto.setPregnancyRecordNo(101);

        when(service.getAllPregnancyCards()).thenReturn(List.of(dto));

        mockMvc.perform(get("/famBridge/pregnancyCard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cid").value(1));
    }

    @Test
    void testGetCardsByRecordNo() throws Exception {
        PregnancyCardDTO dto = new PregnancyCardDTO();
        dto.setPregnancyRecordNo(101);
        dto.setCid(1L);

        when(service.getPregnancyCardsByPregnancyRecordNo(101)).thenReturn(List.of(dto));

        mockMvc.perform(get("/famBridge/pregnancyCard/by-pregnancyRecordNo/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cid").value(1));
    }

    @Test
    void testUpdateByPregnancyRecordNo() throws Exception {
        PregnancyCardDTO dto = new PregnancyCardDTO();
        dto.setPregnancyRecordNo(101);
        dto.setCid(1L);

        when(service.updatePregnancyCardByPregnancyRecordNo(Mockito.eq(101), any())).thenReturn(dto);

        mockMvc.perform(put("/famBridge/pregnancyCard/by-pregnancyRecordNo/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cid").value(1));
    }
}
