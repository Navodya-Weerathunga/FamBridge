package com.edu.famBridge.serviceIMPL;

import com.edu.famBridge.dto.MarriedCoupleRequestDTO;
import com.edu.famBridge.entity.MarriedCoupleRequest;
import com.edu.famBridge.repository.MarriedCoupleRequestRepository;
import com.edu.famBridge.serviceImpl.MarriedCoupleRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MarriedCoupleRequestServiceImplTest {
    @Mock
    private MarriedCoupleRequestRepository marriedCoupleRequestRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MarriedCoupleRequestServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMarriedCoupleRequest_NewRequest_Success() {
        // Arrange
        MarriedCoupleRequestDTO inputDTO = new MarriedCoupleRequestDTO();
        inputDTO.setNic("199850456879");

        MarriedCoupleRequest mappedEntity = new MarriedCoupleRequest();

        when(marriedCoupleRequestRepository.findByNic("199850456879")).thenReturn(Optional.empty());
        when(modelMapper.map(any(MarriedCoupleRequestDTO.class), eq(MarriedCoupleRequest.class)))
                .thenReturn(mappedEntity);

        // Act
        MarriedCoupleRequestDTO result = service.saveMarriedCoupleRequest(inputDTO);

        // Assert
        assertEquals(200, result.getStatusCode());
        assertEquals("Request Sent Successfully.", result.getMessage());
        verify(marriedCoupleRequestRepository, times(1)).save(mappedEntity);
    }

    @Test
    void testSaveMarriedCoupleRequest_AlreadyExists() {
        // Arrange
        MarriedCoupleRequestDTO inputDTO = new MarriedCoupleRequestDTO();
        inputDTO.setNic("987654321V");

        MarriedCoupleRequest existing = new MarriedCoupleRequest();
        existing.setNic("987654321V");

        when(marriedCoupleRequestRepository.findByNic("987654321V"))
                .thenReturn(Optional.of(existing));

        // Act
        MarriedCoupleRequestDTO result = service.saveMarriedCoupleRequest(inputDTO);

        // Assert
        assertEquals("You have already requested", result.getMessage());
        assertEquals(0, result.getStatusCode()); // Default value since not set
        verify(marriedCoupleRequestRepository, never()).save(any());
    }


    @Test
    public void testGetMarriedCoupleRequestDetails() {
        String mohArea = "Battaramulla";

        Object[] row = new Object[]{
                1L, // requestId
                "Anuradhi",
                "Malshika",
                "anuradhi99@gmail.com",
                "199850456879",
                "Battaramulla",
                "Division A",
                "123, Main Street",
                "2024-05-06",
                "PENDING"
        };

        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(row);

        when(marriedCoupleRequestRepository.findRequestDetailsByMohArea("Battaramulla"))
                .thenReturn(mockResults);

        List<String> result = service.getMarriedCoupleRequestDetails("Battaramulla");

        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("Anuradhi"));
        assertTrue(result.get(0).contains("Malshika"));
        assertTrue(result.get(0).contains("Battaramulla"));
    }

    @Test
    void testGetMarriedCoupleRequestDetailsById() {
        // Mock data
        Object[] row = new Object[]{
                "Anuradhi",           // first_name
                "Malshika",            // last_name
                "anuradhi99@gmail.com", // email
                "199850456879",      // nic
                "Battaramulla",        // moh_area
                "Division A",     // grama_niladhari_division
                "123, Main Street",  // address
                "2024-05-06"      // request_date
        };

        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(row);

        // Mocking the repository
        when(marriedCoupleRequestRepository.findRequestDetailsById(1L))
                .thenReturn(mockResults);

        // Method call
        List<String> result = service.getMarriedCoupleRequestDetailsById(1L);

        // Assertions
        assertEquals(1, result.size());
        String expected = "Anuradhi, Malshika, anuradhi99@gmail.com, 199850456879, Battaramulla, Division A, 123, Main Street, 2024-05-06";
        assertEquals(expected, result.get(0));
    }

    @Test
    void testGetMarriedCoupleRequestDetailsById_EmptyResult() {
        // Mocking an empty result
        when(marriedCoupleRequestRepository.findRequestDetailsById(1L))
                .thenReturn(Collections.emptyList());

        // Method call
        List<String> result = service.getMarriedCoupleRequestDetailsById(1L);

        // Assertions
        assertTrue(result.isEmpty());
    }
}
