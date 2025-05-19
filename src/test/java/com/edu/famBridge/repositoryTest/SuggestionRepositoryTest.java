package com.edu.famBridge.repositoryTest;

import com.edu.famBridge.entity.Suggestion;
import com.edu.famBridge.repository.SuggestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SuggestionRepositoryTest {

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Test
    void testFindByEmail() {
        Suggestion s = new Suggestion("Feedback", "test@example.com");
        suggestionRepository.save(s);

        List<Suggestion> results = suggestionRepository.findByEmail("test@example.com");

        assertEquals(1, results.size());
        assertEquals("Feedback", results.get(0).getText());
    }
}
