package com.example.ExeptionHandel;

public class PasswordMismatchException extends ApplicationException {

    public PasswordMismatchException(String message) {
        super(message, "PASSWORD_MISMATCH");
    }
}
