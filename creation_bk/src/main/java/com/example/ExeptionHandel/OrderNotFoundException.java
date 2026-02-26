package com.example.ExeptionHandel;


public class OrderNotFoundException extends ApplicationException {

    public OrderNotFoundException(String message) {
        super(message, "ORDER_NOT_FOUND");
    }
}