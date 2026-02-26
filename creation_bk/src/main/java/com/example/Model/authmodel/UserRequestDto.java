package com.example.Model.authmodel;

public class UserRequestDto {

    private String fullName;
    private String email;
    private String mobileNo;
    private String username;
    private String password;
    private String confoPassword;
    
    @Override
    public String toString() {
        return "UserRequestDto{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confoPassword='" + confoPassword + '\'' +
                '}';
    }

    // No-args constructor
    public UserRequestDto() {
    }

    // All-args constructor
    public UserRequestDto(String fullName, String email, String mobileNo,
                          String username, String password, String confoPassword) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.username = username;
        this.password = password;
        this.confoPassword = confoPassword;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfoPassword() {
        return confoPassword;
    }

    public void setConfoPassword(String confoPassword) {
        this.confoPassword = confoPassword;
    }
}
