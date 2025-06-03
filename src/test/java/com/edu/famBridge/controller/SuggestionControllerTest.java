package com.edu.famBridge.controller;

import com.edu.famBridge.dto.SuggestionDTO;
import com.edu.famBridge.serviceImpl.SuggestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SuggestionControllerTest {

    @InjectMocks
    private SuggestionController suggestionController;

    @Mock
    private SuggestionService suggestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSuggestions() {
        SuggestionDTO dto = new SuggestionDTO(1L, "Sample", LocalDateTime.now(), "user@example.com");
        when(suggestionService.getAllSuggestions()).thenReturn(List.of(dto));

        ResponseEntity<List<SuggestionDTO>> response = suggestionController.getAllSuggestions();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testAddSuggestion() {
        SuggestionDTO dto = new SuggestionDTO(null, "New suggestion", LocalDateTime.now(), "user@example.com");
        when(suggestionService.addSuggestion(any())).thenReturn(dto);

        ResponseEntity<SuggestionDTO> response = suggestionController.addSuggestion(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("New suggestion", response.getBody().getText());
    }

    @Test
    void testUpdateSuggestion() {
        SuggestionDTO dto = new SuggestionDTO(1L, "Updated", LocalDateTime.now(), "user@example.com");
        when(suggestionService.updateSuggestion(1L, "Updated")).thenReturn(dto);

        ResponseEntity<SuggestionDTO> response = suggestionController.updateSuggestion(1L, dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody().getText());
    }

    @Test
    void testDeleteSuggestion() {
        doNothing().when(suggestionService).deleteSuggestion(1L);

        ResponseEntity<String> response = suggestionController.deleteSuggestion(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Suggestion deleted successfully.", response.getBody());
    }

    @Test
    void testGetFeedbacksByEmail() {
        String email = "test@example.com";
        when(suggestionService.getSuggestionsByEmail(email)).thenReturn(List.of());

        List<SuggestionDTO> result = suggestionController.getFeedbacksByEmail(email);
        assertNotNull(result);
    }
}
