package com.example.ExeptionHandel;
public class CategoryAlreadyExistsException extends RuntimeException{
	public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}