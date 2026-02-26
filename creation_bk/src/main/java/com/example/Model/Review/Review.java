package com.example.Model.Review;

import java.time.LocalDateTime;

import com.example.Model.authmodel.UserModel;
import com.example.Model.products.Products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "product_reviews",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "user_id"})
    }
)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Integer stars; // 1 to 5

    @Column(length = 1000)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public Integer getStars() { return stars; }
    public String getMessage() { return message; }
    public Products getProduct() { return product; }
    public UserModel getUser() { return user; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // --- Setters ---

    public void setId(Long id) { this.id = id; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setStars(Integer stars) { this.stars = stars; }
    public void setMessage(String message) { this.message = message; }
    public void setProduct(Products product) { this.product = product; }
    public void setUser(UserModel user) { this.user = user; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // getters & setters
}
