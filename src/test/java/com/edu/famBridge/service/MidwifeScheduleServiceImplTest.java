package com.edu.famBridge.service;

import com.edu.famBridge.dto.MidwifeScheduledto;
import com.edu.famBridge.entity.MidwifeSchedule;
import com.edu.famBridge.repository.MidwifeScheduleRepository;
import com.edu.famBridge.serviceImpl.MidwifeScheduleServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.modelmapper.ModelMapper;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MidwifeScheduleServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private MidwifeScheduleRepository midwifeScheduleRepository;

    @InjectMocks
    private MidwifeScheduleServiceImpl service;


    private MidwifeScheduledto sampleDto;
    private MidwifeSchedule existingEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleDto = new MidwifeScheduledto();
        sampleDto.setId(1L);
        sampleDto.setMidwifeId("MW001");
        sampleDto.setMidwifeName("Jane Doe");
        sampleDto.setAvailableDate(LocalDate.of(2025, 5, 15));
        sampleDto.setStartTime(LocalTime.of(9, 0));
        sampleDto.setEndTime(LocalTime.of(15, 0));
        sampleDto.setAppointmentsPerDay(6);
        sampleDto.setWorkType("Clinic");
        sampleDto.setArea("Colombo");

        existingEntity = new MidwifeSchedule();
        existingEntity.setId(1L);
        existingEntity.setMidwifeId("MW001");
        existingEntity.setMidwifeName("Jane Doe");
        existingEntity.setAvailableDate(LocalDate.of(2025, 5, 15));
        existingEntity.setStartTime(LocalTime.of(9, 0));
        existingEntity.setEndTime(LocalTime.of(15, 0));
        existingEntity.setAppointmentsPerDay(6);
        existingEntity.setWorkType("Clinic");
        existingEntity.setArea("Colombo");
    }

    @Test
    void testAddOrUpdateAppointment_ValidData_ReturnsSavedAppointment() {
        when(midwifeScheduleRepository.save(any(MidwifeSchedule.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MidwifeSchedule result = service.addOrUpdateAppointment(sampleDto);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getMidwifeName());
        assertEquals("Colombo", result.getArea());
        assertEquals(LocalTime.of(9, 0), result.getStartTime());

        System.out.println("Saved record successfully.");
    }

    @Test
    void testAddOrUpdateAppointment_MissingFields_ThrowsException() {
        MidwifeScheduledto invalidDto = new MidwifeScheduledto(); // all fields null

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addOrUpdateAppointment(invalidDto);
        });

        assertEquals("All fields are required and cannot be null", exception.getMessage());
        System.out.println("All fields are required and cannot be null");
    }

    @Test
    void testAddOrUpdateAppointment_EndTimeBeforeStartTime_ThrowsException() {
        sampleDto.setStartTime(LocalTime.of(16, 0));
        sampleDto.setEndTime(LocalTime.of(10, 0)); // invalid range

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addOrUpdateAppointment(sampleDto);
        });

        assertEquals("End time cannot be before start time", exception.getMessage());
        System.out.println("End time cannot be before start time");
    }

    @Test
    public void testUpdateAppointment_Success() {
        // Arrange: mock repository and mapper behaviors
        when(midwifeScheduleRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
        when(midwifeScheduleRepository.save(any(MidwifeSchedule.class))).thenReturn(existingEntity);
        when(modelMapper.map(existingEntity, MidwifeScheduledto.class)).thenReturn(sampleDto);

        // Act: call the service method
        MidwifeScheduledto updated = service.updateAppointmentByID(1L, sampleDto);

        // Assert: validate that the updated values match the expected result
        assertNotNull(updated);
        assertEquals(1L, updated.getId());
        assertEquals("MW001", updated.getMidwifeId());
        assertEquals("Jane Doe", updated.getMidwifeName());
        assertEquals("2025-05-07", updated.getAvailableDate());
        assertEquals("08:00", updated.getStartTime());
        assertEquals("16:00", updated.getEndTime());
        assertEquals(5, updated.getAppointmentsPerDay());
        assertEquals("General", updated.getWorkType());
        assertEquals("Area A", updated.getArea());

        System.out.println("Update success");
    }

    @Test
    void testUpdateAppointment_NotFound() {
        when(midwifeScheduleRepository.findById(999L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            service.updateAppointmentByID(999L, sampleDto);
        });

        assertEquals("Appointment with ID 999 not found", exception.getMessage());
        System.out.println("Appointment with ID 999 not found");
    }

    @Test
    void testUpdateAppointment_NullDTO() {
        assertThrows(NullPointerException.class, () -> {
            service.updateAppointmentByID(1L, null);
        });
        System.out.println("DTO is null");
    }

    @Test
    void testUpdateAppointment_InvalidTimeRange() {
        sampleDto.setStartTime(LocalTime.of(15, 0));
        sampleDto.setEndTime(LocalTime.of(9, 0)); // invalid range

        when(midwifeScheduleRepository.findById(1L)).thenReturn(Optional.of(existingEntity));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateAppointmentByID(1L, sampleDto);
        });

        assertEquals("End time cannot be before start time", exception.getMessage());
        System.out.println("Invalid time range");
    }

    @Test
    void testUpdateAppointment_NegativeAppointments() {
        sampleDto.setAppointmentsPerDay(-3); // invalid value

        when(midwifeScheduleRepository.findById(1L)).thenReturn(Optional.of(existingEntity));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateAppointmentByID(1L, sampleDto);
        });

        assertEquals("Appointments per day cannot be negative", exception.getMessage());
        System.out.println("Appointments per day cannot be negative");
    }

    @Test
    void testDeleteAppointment_Success() {
        Long id = 1L;
        when(midwifeScheduleRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> service.deleteAppointment(id));
        verify(midwifeScheduleRepository).deleteById(id);
        System.out.println("Success delete");
    }

    @Test
    void testDeleteAppointment_NullId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                service.deleteAppointment(null));
        assertEquals("Appointment ID cannot be null", exception.getMessage());
        System.out.println("Appointment ID cannot be null");
    }

    @Test
    void testDeleteAppointment_IdNotFound() {
        Long id = 99L;
        when(midwifeScheduleRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                service.deleteAppointment(id));
        assertEquals("Appointment with ID 99 does not exist", exception.getMessage());
        System.out.println("Appointment with ID 99 does not exist");
    }
    @Test
    void testGetAllAppointments_ReturnsList() {
        List<MidwifeSchedule> mockList = List.of(new MidwifeSchedule(), new MidwifeSchedule());
        when(midwifeScheduleRepository.findAll()).thenReturn(mockList);

        List<MidwifeSchedule> result = service.getAllAppointments();

        assertEquals(2, result.size());
        System.out.println("succsess return");
    }

    @Test
    void testGetAllAppointments_ReturnsEmptyList() {
        when(midwifeScheduleRepository.findAll()).thenReturn(Collections.emptyList());

        List<MidwifeSchedule> result = service.getAllAppointments();

        assertTrue(result.isEmpty());
        System.out.println("Empty list is retrun");
    }

    @Test
    void testGetAllAppointments_RepositoryThrowsException() {
        when(midwifeScheduleRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> service.getAllAppointments());
        System.out.println("throw error exection");
    }


    @Test
    void testGetAppointmentByID_NullResult() {
        when(midwifeScheduleRepository.getappointmentID(1L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            service.getAppointmentByID(1L);
        });
        System.out.println("No Result Found null only");
    }



    @Test
    void testGetLatestAppointmentId_NotFound() {
        when(midwifeScheduleRepository.findLatestOrderIdByIndexNo("MW001")).thenReturn(null);

        MidwifeScheduledto result = service.getLatestAppointmentId("MW001");
        assertNull(result);
        System.out.println("latest appointment not found");
    }

    @Test
    void testGetMidwife_Invalid() {
        when(midwifeScheduleRepository.getMidwife("Invalid", 999L)).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            service.getMidwife("Invalid", 999L);
        });
        System.out.println("Invalid midwife get");
    }

    @Test
    void testGetAppointmentByID_ValidId_ReturnsDto() {
        Long validId = 1L;

        // Mock a valid appointment entity
        MidwifeSchedule appointment = new MidwifeSchedule();
        appointment.setId(validId);
        appointment.setMidwifeId("MW001");
        appointment.setMidwifeName("Jane Doe");
        appointment.setAvailableDate(LocalDate.of(2025, 5, 15));
        appointment.setStartTime(LocalTime.of(9, 0));
        appointment.setEndTime(LocalTime.of(15, 0));
        appointment.setAppointmentsPerDay(6);
        appointment.setWorkType("Clinic");
        appointment.setArea("Colombo");

        // Expected DTO
        MidwifeScheduledto expectedDto = new MidwifeScheduledto();
        expectedDto.setId(validId);
        expectedDto.setMidwifeId("MW001");
        expectedDto.setMidwifeName("Jane Doe");
        expectedDto.setAvailableDate(LocalDate.of(2025, 5, 15));
        expectedDto.setStartTime(LocalTime.of(9, 0));
        expectedDto.setEndTime(LocalTime.of(15, 0));
        expectedDto.setAppointmentsPerDay(6);
        expectedDto.setWorkType("Clinic");
        expectedDto.setArea("Colombo");

        // Mock repository and model mapper behavior
        when(midwifeScheduleRepository.getappointmentID(validId)).thenReturn(appointment);
        when(modelMapper.map(appointment, MidwifeScheduledto.class)).thenReturn(expectedDto);

        // Act
        MidwifeScheduledto result = service.getAppointmentByID(validId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDto.getMidwifeName(), result.getMidwifeName());
        assertEquals(expectedDto.getArea(), result.getArea());
        assertEquals(expectedDto.getAvailableDate(), result.getAvailableDate());

        System.out.println("Valid appointment retrieved successfully");
    }



}
