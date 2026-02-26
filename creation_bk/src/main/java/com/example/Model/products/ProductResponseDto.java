package com.example.Model.products;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponseDto {
	
    private Long id;  //  //
    private String productName;  //
    
    private String disc;
    

    private Long categoryId;
    private String categoryName;

    private String mainImgUrl;
    private String image1Url;
    private String image2Url;

    private Boolean isAvailable;
    private BigDecimal price;
    private String specification;

    private LocalDateTime createdAt;   //

    // No-args constructor
    public ProductResponseDto() {
    }

    // All-args constructor
    public ProductResponseDto(Long id, String productName,
                              Long categoryId, String categoryName,
                              String mainImgUrl, String image1Url, String image2Url,
                              Boolean isAvailable, BigDecimal price,
                              String specification, LocalDateTime createdAt,String disc) {

        this.id = id;
        this.productName = productName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.mainImgUrl = mainImgUrl;
        this.image1Url = image1Url;
        this.image2Url = image2Url;
        this.isAvailable = isAvailable;
        this.price = price;
        this.specification = specification;
        this.createdAt = createdAt;
        this.disc=disc;
    }
    public ProductResponseDto(Products product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.categoryId = product.getCategoryId();
        this.categoryName = product.getCategoryName();
        this.mainImgUrl = product.getMainImg();
        this.image1Url = product.getImage1();
        this.image2Url = product.getImage2();
        this.isAvailable = product.isAvailable();
        this.price = product.getPrice();
        this.specification = product.getSpecification();
        this.createdAt = product.getCreatedAt();
        this.disc = product.getDisc(); 
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

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public String getImage1Url() {
        return image1Url;
    }

    public void setImage1Url(String image1Url) {
        this.image1Url = image1Url;
    }

    public String getImage2Url() {
        return image2Url;
    }

    public void setImage2Url(String image2Url) {
        this.image2Url = image2Url;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
