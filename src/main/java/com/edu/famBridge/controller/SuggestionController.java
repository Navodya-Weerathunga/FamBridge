package com.edu.famBridge.controller;


import com.edu.famBridge.dto.SuggestionDTO;

import com.edu.famBridge.dto.VisitChannelingdto;
import com.edu.famBridge.serviceImpl.SuggestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    //  Get all suggestions
    @GetMapping
    public ResponseEntity<List<SuggestionDTO>> getAllSuggestions() {
        return ResponseEntity.ok(suggestionService.getAllSuggestions());
    }

    //  Get suggestion by ID


    //  Add a new suggestion
    @PostMapping
    public ResponseEntity<SuggestionDTO> addSuggestion(@RequestBody SuggestionDTO suggestionDTO) {
        return ResponseEntity.ok(suggestionService.addSuggestion(suggestionDTO));
    }

    // Update suggestion
    @PutMapping("/{id}")
    public ResponseEntity<SuggestionDTO> updateSuggestion(@PathVariable Long id, @RequestBody SuggestionDTO suggestionDTO) {
        SuggestionDTO updatedSuggestion = suggestionService.updateSuggestion(id, suggestionDTO.getText());
        if (updatedSuggestion != null) {
            return ResponseEntity.ok(updatedSuggestion);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete suggestion
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSuggestion(@PathVariable Long id) {
        suggestionService.deleteSuggestion(id);
        return ResponseEntity.ok("Suggestion deleted successfully.");
    }

    // Update suggestion status (New feature!)
    @PatchMapping("/{id}/status")
    public ResponseEntity<SuggestionDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
        // Add logic for updating status (not yet implemented in service)
        return ResponseEntity.ok(null);
    }



    @GetMapping("/getFeedback/{email}")
    public List<SuggestionDTO> getFeedbacksByEmail(@Valid @PathVariable String email) {
        return suggestionService. getSuggestionsByEmail(email);
    }

}
