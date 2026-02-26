package com.example.Model.Otp;


public class OtpDetails {

    private String otp;
    private long expiryTime;   // e.g., System.currentTimeMillis() + 5*60*1000

    // No-argument constructor
    public OtpDetails() {
    }

    // Parameterized constructor
    public OtpDetails(String otp, long expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    // Getter for otp
    public String getOtp() {
        return otp;
    }

    // Setter for otp
    public void setOtp(String otp) {
        this.otp = otp;
    }

    // Getter for expiryTime
    public long getExpiryTime() {
        return expiryTime;
    }

    // Setter for expiryTime
    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

}
