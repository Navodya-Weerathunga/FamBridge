package com.edu.famBridge.service;

import com.edu.famBridge.dto.SuggestionDTO;
import com.edu.famBridge.entity.Suggestion;
import com.edu.famBridge.repository.SuggestionRepository;
import com.edu.famBridge.serviceImpl.SuggestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SuggestionServiceTest {

    @InjectMocks
    private SuggestionService suggestionService;

    @Mock
    private SuggestionRepository suggestionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSuggestions() {
        Suggestion suggestion = new Suggestion("Text", "email");
        when(suggestionRepository.findAll()).thenReturn(List.of(suggestion));

        List<SuggestionDTO> result = suggestionService.getAllSuggestions();

        assertEquals(1, result.size());
    }

    @Test
    void testAddSuggestion() {
        SuggestionDTO dto = new SuggestionDTO(null, "New", LocalDateTime.now(), "email");
        when(suggestionRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        SuggestionDTO result = suggestionService.addSuggestion(dto);
        assertEquals("New", result.getText());
    }

    @Test
    void testUpdateSuggestionFound() {
        Suggestion existing = new Suggestion("Old", "email");
        existing.setId(1L);
        when(suggestionRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(suggestionRepository.save(any())).thenReturn(existing);

        SuggestionDTO updated = suggestionService.updateSuggestion(1L, "Updated");

        assertEquals("Updated", updated.getText());
    }

    @Test
    void testUpdateSuggestionNotFound() {
        when(suggestionRepository.findById(99L)).thenReturn(Optional.empty());

        SuggestionDTO result = suggestionService.updateSuggestion(99L, "Doesn't exist");
        assertNull(result);
    }

    @Test
    void testDeleteSuggestion() {
        doNothing().when(suggestionRepository).deleteById(1L);
        suggestionService.deleteSuggestion(1L);
        verify(suggestionRepository, times(1)).deleteById(1L);
    }
}
