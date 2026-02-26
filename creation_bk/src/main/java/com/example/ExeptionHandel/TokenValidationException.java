package com.example.ExeptionHandel;

public class TokenValidationException extends ApplicationException {

    public TokenValidationException(String message) {
        super(message, "TOKEN_INVALID");
    }
}
