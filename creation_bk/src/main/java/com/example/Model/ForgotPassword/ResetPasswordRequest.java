package com.example.Model.ForgotPassword;
public record ResetPasswordRequest(String email, String otp,String newPassword) {}