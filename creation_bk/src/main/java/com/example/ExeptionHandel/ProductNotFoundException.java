package com.example.ExeptionHandel;

public class ProductNotFoundException extends ApplicationException {

	public ProductNotFoundException(String message) {
        super(message, "PASSWORD_MISMATCH");
    }
}
