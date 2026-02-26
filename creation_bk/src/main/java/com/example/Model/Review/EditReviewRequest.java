package com.example.Model.Review;
public class EditReviewRequest {

    private Long reviewId;
    private String message;
    private int rating; // 1–5

    // No-Args Constructor
    public EditReviewRequest() {
    }

    // All-Args Constructor
    public EditReviewRequest(Long reviewId, String message, int rating) {
        this.reviewId = reviewId;
        this.message = message;
        this.rating = rating;
    }

    // --- Getters ---
    public Long getReviewId() { return reviewId; }
    public String getMessage() { return message; }
    public int getRating() { return rating; }

    // --- Setters ---
    public void setReviewId(Long reviewId) { this.reviewId = reviewId; }
    public void setMessage(String message) { this.message = message; }
    public void setRating(int rating) { this.rating = rating; }
}