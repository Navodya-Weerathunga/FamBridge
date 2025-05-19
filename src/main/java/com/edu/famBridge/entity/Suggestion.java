package com.edu.famBridge.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "suggestions")
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private LocalDateTime date;

    private String email;  // Changed from Long to String (correct type)

    // Default constructor
    public Suggestion() {
        this.date = LocalDateTime.now(); // Auto set date
    }

    // Parameterized constructor
    public Suggestion(String text, String email) {
        this.text = text;
        this.email = email;
        this.date = LocalDateTime.now();
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

    public String getEmail() {  // Fixed getter name
        return email;
    }

    public void setEmail(String email) {  // Fixed setter name
        this.email = email;
    }
}
