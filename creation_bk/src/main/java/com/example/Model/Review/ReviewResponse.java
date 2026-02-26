package com.example.Model.Review;

import java.time.LocalDateTime;

public class ReviewResponse {

    private Long reviewId;      // ✅ Added
    private Long productId;     // ✅ Added
    private String productName;
    private String category;
    private String userName;
    private Integer stars;
    private String message;
    private LocalDateTime createdAt;

    // No-Args Constructor
    public ReviewResponse() {
    }

    // All-Args Constructor
    public ReviewResponse(Long reviewId,
                          Long productId,
                          String productName,
                          String category,
                          String userName,
                          Integer stars,
                          String message,
                          LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.userName = userName;
        this.stars = stars;
        this.message = message;
        this.createdAt = createdAt;
    }

    // --- Getters ---
    public Long getReviewId() { return reviewId; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public String getUserName() { return userName; }
    public Integer getStars() { return stars; }
    public String getMessage() { return message; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // --- Setters ---
    public void setReviewId(Long reviewId) { this.reviewId = reviewId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setStars(Integer stars) { this.stars = stars; }
    public void setMessage(String message) { this.message = message; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
