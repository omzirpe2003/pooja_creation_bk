package com.example.Model.SupportMessage;

import java.time.LocalDateTime;

import com.example.enums.StatusOfContect;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity
public class ContectUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusOfContect status = StatusOfContect.PENDING;

    // No-argument constructor
    public ContectUs() {
    }

    // All-argument constructor
    public ContectUs(Long id, String name, String email, String subject,
            String message, LocalDateTime createdAt, StatusOfContect status) {
this.id = id;
this.name = name;
this.email = email;
this.subject = subject;
this.message = message;
this.createdAt = createdAt;
this.status = status;
}


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public StatusOfContect getStatus() {
        return status;
    }

    public void setStatus(StatusOfContect status) {
        this.status = status;
    }
}