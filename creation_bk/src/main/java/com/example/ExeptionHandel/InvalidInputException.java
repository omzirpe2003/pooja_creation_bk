// com.example.exception.InvalidInputException.java
package com.example.ExeptionHandel;

public class InvalidInputException extends ApplicationException {
    public InvalidInputException(String message) {
        super(message, "INPUT_001");
    }
}