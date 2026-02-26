package com.example.ExeptionHandel;

public class InvalidCredentialsException extends ApplicationException {

    public InvalidCredentialsException(String message) {
        super(message, "INVALID_CREDENTIALS");
    }
}
