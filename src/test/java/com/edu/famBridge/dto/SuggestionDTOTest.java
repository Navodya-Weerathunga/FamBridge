package com.edu.famBridge.dto;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SuggestionDTOTest {

    @Test
    void testDTOFields() {
        LocalDateTime now = LocalDateTime.now();
        SuggestionDTO dto = new SuggestionDTO(1L, "Text", now, "email");

        assertEquals(1L, dto.getId());
        assertEquals("Text", dto.getText());
        assertEquals("email", dto.getEmail());
        assertEquals(now, dto.getDate());
    }
}
