package com.edu.famBridge.dto;

import java.time.LocalDateTime;

public class SuggestionDTO {
    private Long id;
    private String text;
    private LocalDateTime date;
    private String email; // Matched with Entity

    // Default constructor
    public SuggestionDTO() {}

    // Parameterized constructor
    public SuggestionDTO(Long id, String text, LocalDateTime date, String email) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
