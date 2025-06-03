package com.edu.famBridge.controller;

import com.edu.famBridge.dto.MidwifeScheduledto;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.service.MidwifeScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MidwifeScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MidwifeScheduleService midwifeScheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddAppointment() throws Exception {
        MidwifeScheduledto dto = new MidwifeScheduledto();
        MidwifeSchedule schedule = new MidwifeSchedule();

        Mockito.when(midwifeScheduleService.addOrUpdateAppointment(any())).thenReturn(schedule);

        mockMvc.perform(post("/famBridge/MidwifeSchedule/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllAppointments() throws Exception {
        Mockito.when(midwifeScheduleService.getAllAppointments()).thenReturn(List.of(new MidwifeSchedule()));

        mockMvc.perform(get("/famBridge/MidwifeSchedule/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAppointment() throws Exception {
        mockMvc.perform(delete("/famBridge/MidwifeSchedule/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Appointment deleted successfully"));
    }

    @Test
    void testGetAppointmentById() throws Exception {
        Mockito.when(midwifeScheduleService.getAppointmentByID(1L)).thenReturn(new MidwifeScheduledto());

        mockMvc.perform(get("/famBridge/MidwifeSchedule/getAppointmentById/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateAppointmentByID() throws Exception {
        MidwifeScheduledto dto = new MidwifeScheduledto();
        Mockito.when(midwifeScheduleService.updateAppointmentByID(eq(1L), any())).thenReturn(dto);

        mockMvc.perform(put("/famBridge/MidwifeSchedule/updateAppointmentByID/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetLatestAppointmentId() throws Exception {
        Mockito.when(midwifeScheduleService.getLatestAppointmentId("mid123")).thenReturn(new MidwifeScheduledto());

        mockMvc.perform(get("/famBridge/MidwifeSchedule/getLatestAppointmentId/mid123"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMidwifeByMidwifename() throws Exception {
        Mockito.when(midwifeScheduleService.getMidwifeByMidwifename("Jane"))
                .thenReturn(List.of(new MidwifeScheduledto()));

        mockMvc.perform(get("/famBridge/MidwifeSchedule/getMidwifeByMidwifename/Jane"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMidwifeID() throws Exception {
        Mockito.when(midwifeScheduleService.getMidwifeID(any(), eq("Jane")))
                .thenReturn(new MidwifeScheduledto());

        mockMvc.perform(get("/famBridge/MidwifeSchedule/getMidwifeID/2024-12-01/Jane"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMidwifeArea() throws Exception {
        Mockito.when(midwifeScheduleService.getMidwifeArea("mid123", "Colombo"))
                .thenReturn(List.of(new MidwifeScheduledto()));

        mockMvc.perform(get("/famBridge/MidwifeSchedule/getMidwifeArea/mid123/Colombo"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMidwifeByNameAndId() throws Exception {
        Mockito.when(midwifeScheduleService.getMidwife("Jane", 1L)).thenReturn(new MidwifeScheduledto());

        mockMvc.perform(get("/famBridge/MidwifeSchedule/getMidwife/Jane/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMidwifeDate() throws Exception {
        Mockito.when(midwifeScheduleService.getMidwifeDate(any(), eq("Colombo"))).thenReturn(new MidwifeScheduledto());

        mockMvc.perform(get("/famBridge/MidwifeSchedule/getMidwifeDate/2024-12-01/Colombo"))
                .andExpect(status().isOk());
    }
}