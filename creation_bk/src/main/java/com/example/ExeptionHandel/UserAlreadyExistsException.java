package com.example.ExeptionHandel;

public class UserAlreadyExistsException extends ApplicationException {

    public UserAlreadyExistsException(String message) {
        super(message, "USER_ALREADY_EXISTS");
    }
}
