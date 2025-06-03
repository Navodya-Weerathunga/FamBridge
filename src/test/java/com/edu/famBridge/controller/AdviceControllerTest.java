package com.edu.famBridge.controller;

import com.edu.famBridge.entity.AdviceEntity;
import com.edu.famBridge.service.AdviceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(AdviceController.class)
public class AdviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdviceService adviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddAdvice() throws Exception {
        AdviceEntity advice = new AdviceEntity("Pregnancy", "Stay hydrated.");
        when(adviceService.addAdvice(any(AdviceEntity.class))).thenReturn(advice);

        mockMvc.perform(post("/api/advice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(advice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Pregnancy"))
                .andExpect(jsonPath("$.advice").value("Stay hydrated."));
    }

    @Test
    void testGetAllAdvice() throws Exception {
        List<AdviceEntity> adviceList = Arrays.asList(new AdviceEntity("Pregnancy", "Eat well"));
        when(adviceService.getAllAdvice()).thenReturn(adviceList);

        mockMvc.perform(get("/api/advice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetAdviceByType() throws Exception {
        List<AdviceEntity> adviceList = Arrays.asList(new AdviceEntity("Pregnancy", "Rest enough"));
        when(adviceService.getAdviceByType("Pregnancy")).thenReturn(adviceList);

        mockMvc.perform(get("/api/advice/type/Pregnancy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].advice").value("Rest enough"));
    }

    @Test
    void testUpdateAdvice() throws Exception {
        AdviceEntity updatedAdvice = new AdviceEntity("Postpartum", "Take rest");
        when(adviceService.updateAdvice(eq(1L), any(AdviceEntity.class))).thenReturn(updatedAdvice);

        mockMvc.perform(put("/api/advice/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAdvice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Postpartum"))
                .andExpect(jsonPath("$.advice").value("Take rest"));
    }

    @Test
    void testDeleteAdvice() throws Exception {
        doNothing().when(adviceService).deleteAdvice(1L);

        mockMvc.perform(delete("/api/advice/1"))
                .andExpect(status().isOk());

        verify(adviceService, times(1)).deleteAdvice(1L);
    }
}
