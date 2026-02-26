package com.example.ExeptionHandel;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String message) {
        super(message, "RESOURCE_NOT_FOUND");
    }
}
