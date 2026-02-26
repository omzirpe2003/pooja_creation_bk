
package com.example.Model.authmodel;

public class AuthResponseDto {

    private String token;
    private String username;
    private String role;           // e.g. "USER", "ADMIN"

    // No-args constructor
    public AuthResponseDto() {
    }

    // All-args constructor
    public AuthResponseDto(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
