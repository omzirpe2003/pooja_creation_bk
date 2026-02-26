package com.example.Model.products;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Review.Review;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "main_img")
    private String mainImg;
    
    @Column(name = "disc")
    private String disc;

    @Column(name = "image_1")
    private String image1;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "specification", columnDefinition = "TEXT")
    private String specification;
    
    
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
        private List<Review> reviews = new ArrayList<>();

    // No-args constructor (required by JPA)
    public Products() {
    }
    
    public void addReview(Review review) {
        reviews.add(review);
        review.setProduct(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setProduct(null);
    }

    // All-args constructor
    public Products(Long id, String productName,
                    Long categoryId, String categoryName,
                    String mainImg, String image1, String image2,
                    boolean isAvailable, LocalDateTime createdAt,
                    BigDecimal price, String specification, String disc) {

        this.id = id;
        this.productName = productName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.mainImg = mainImg;
        this.image1 = image1;
        this.image2 = image2;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.price = price;
        this.specification = specification;
        this.disc=disc;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
