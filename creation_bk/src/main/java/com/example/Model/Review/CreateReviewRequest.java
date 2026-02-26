package com.example.Model.Review;

public class CreateReviewRequest {

    private Long productId;
    private String userName;
    private String message;
    private int rating; // 1–5

    // No-Args Constructor
    public CreateReviewRequest() {
    }

    // All-Args Constructor
    public CreateReviewRequest(Long productId, String userName, String message, int rating) {
        this.productId = productId;
        this.userName = userName;
        this.message = message;
        this.rating = rating;
    }

    // --- Getters ---
    public Long getProductId() { return productId; }
    public String getUserName() { return userName; }
    public String getMessage() { return message; }
    public int getRating() { return rating; }

    // --- Setters ---
    public void setProductId(Long productId) { this.productId = productId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setMessage(String message) { this.message = message; }
    public void setRating(int rating) { this.rating = rating; }
}