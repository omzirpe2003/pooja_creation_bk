
package com.example.Model.notification;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "fcm_tokens")
public class FcmToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;      // internal user/vendor ID
    private String token;
    private String type;        // "user" / "vendor"
    private String device;
    private LocalDateTime lastUpdated;

    // ---------- CONSTRUCTORS ----------

    public FcmToken() {
    }

    public FcmToken(Long id, String userId, String token, String type, String device, LocalDateTime lastUpdated) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.type = type;
        this.device = device;
        this.lastUpdated = lastUpdated;
    }

    // ---------- JPA LIFECYCLE ----------

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }

    // ---------- GETTERS ----------

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getDevice() {
        return device;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    // ---------- SETTERS ----------

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
