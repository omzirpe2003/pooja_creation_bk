package com.example.ExeptionHandel;


public class CategoryNotFoundException extends RuntimeException{
	
	public CategoryNotFoundException(String msg) {
		super(msg);
	}
}