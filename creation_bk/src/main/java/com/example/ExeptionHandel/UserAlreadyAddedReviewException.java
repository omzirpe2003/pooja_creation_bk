package com.example.ExeptionHandel;

public class UserAlreadyAddedReviewException extends ApplicationException {

    public UserAlreadyAddedReviewException(String message) {
        super(message, "USER_ALREADY_ADDED_REVIEW");
    }
}
