package com.example.Model.authmodel;

import java.time.LocalDateTime;

public class UserResponseDto {

    private Long id;
    private String fullName;
    private String mobileNo;
    private String username;
    private String emailId;
    private String roleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // No-Args Constructor
    public UserResponseDto() {
    }

    // All-Args Constructor
    public UserResponseDto(Long id,
                           String fullName,
                           String mobileNo,
                           String username,
                           String emailId,
                           String roleName,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.username = username;
        this.emailId = emailId;
        this.roleName = roleName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
