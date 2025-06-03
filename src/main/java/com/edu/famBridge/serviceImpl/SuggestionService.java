package com.edu.famBridge.serviceImpl;


import com.edu.famBridge.dto.SuggestionDTO;
import com.edu.famBridge.dto.VisitChannelingdto;
import com.edu.famBridge.entity.Suggestion;
import com.edu.famBridge.entity.VisitChanneling;
import com.edu.famBridge.repository.SuggestionRepository;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;

    // Convert Suggestion entity to DTO
    private SuggestionDTO convertToDTO(Suggestion suggestion) {
        return new SuggestionDTO(
                suggestion.getId(),
                suggestion.getText(),
                suggestion.getDate(),
                suggestion.getEmail()
        );
    }

    // Convert SuggestionDTO to entity
    private Suggestion convertToEntity(SuggestionDTO suggestionDTO) {
        return new Suggestion(
                suggestionDTO.getText(),
                suggestionDTO.getEmail()
        );
    }

    // Get all suggestions as DTOs
    public List<SuggestionDTO> getAllSuggestions() {
        List<Suggestion> suggestions = suggestionRepository.findAll();
        return suggestions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get suggestions by email as DTOs
    public List<SuggestionDTO> getSuggestionsByEmail(String email) {
        List<Suggestion> suggestions = suggestionRepository.findByEmail(email);
        return suggestions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Add a new suggestion
    public SuggestionDTO addSuggestion(SuggestionDTO suggestionDTO) {
        Suggestion suggestion = convertToEntity(suggestionDTO);
        suggestion = suggestionRepository.save(suggestion);
        return convertToDTO(suggestion);
    }

    // Update an existing suggestion
    public SuggestionDTO updateSuggestion(Long id, String text) {
        Optional<Suggestion> optionalSuggestion = suggestionRepository.findById(id);
        if (optionalSuggestion.isPresent()) {
            Suggestion suggestion = optionalSuggestion.get();
            suggestion.setText(text);
            suggestion = suggestionRepository.save(suggestion);
            return convertToDTO(suggestion);
        }
        return null;  // If suggestion not found
    }

    // Delete a suggestion by ID
    public void deleteSuggestion(Long id) {
        suggestionRepository.deleteById(id);
    }


}
