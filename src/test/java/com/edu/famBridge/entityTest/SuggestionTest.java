package com.edu.famBridge.entityTest;

import com.edu.famBridge.entity.Suggestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuggestionTest {

    @Test
    void testEntityDefaultConstructor() {
        Suggestion suggestion = new Suggestion();
        assertNotNull(suggestion.getDate());
    }

    @Test
    void testEntityParameterizedConstructor() {
        Suggestion suggestion = new Suggestion("Text", "email");

        assertEquals("Text", suggestion.getText());
        assertEquals("email", suggestion.getEmail());
        assertNotNull(suggestion.getDate());
    }
}
