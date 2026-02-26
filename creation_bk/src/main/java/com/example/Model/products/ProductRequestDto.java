package com.example.Model.products;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class ProductRequestDto {

    private String productName;

    private String categoryName;

    private MultipartFile mainImg;
    private MultipartFile image1;
    private MultipartFile image2;

    private Boolean isAvailable;

    private BigDecimal price;
    private String specification;

    private String disc;
    
    // No-args constructor
    public ProductRequestDto() {
    }

    // All-args constructor
    public ProductRequestDto(String productName, String categoryName,
                             MultipartFile mainImg, MultipartFile image1, MultipartFile image2,
                             Boolean isAvailable, BigDecimal price, String specification,String disc) {

        this.productName = productName;
        this.categoryName = categoryName;
        this.mainImg = mainImg;
        this.image1 = image1;
        this.image2 = image2;
        this.isAvailable = isAvailable;
        this.price = price;
        this.specification = specification;
        this.disc=disc;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
    
    public String getDisc() {
        return disc;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public MultipartFile getMainImg() {
        return mainImg;
    }

    public void setMainImg(MultipartFile mainImg) {
        this.mainImg = mainImg;
    }

    public MultipartFile getImage1() {
        return image1;
    }

    public void setImage1(MultipartFile image1) {
        this.image1 = image1;
    }

    public MultipartFile getImage2() {
        return image2;
    }

    public void setImage2(MultipartFile image2) {
        this.image2 = image2;
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
}
