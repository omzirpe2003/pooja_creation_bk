
package com.example.Model.orders;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long productId;
    private String productName;
    private String mainImage;
    private String image2;
    private String image3;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String category;
    private Long categoryId;
    private BigDecimal totalPrice;

    // No-Args Constructor
    public OrderItemResponse() {
    }

    // All-Args Constructor
    public OrderItemResponse(Long productId, String productName, Integer quantity, 
                             BigDecimal unitPrice, String category, Long categoryId, 
                             BigDecimal totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.category = category;
        this.categoryId = categoryId;
        this.totalPrice = totalPrice;
    }

    // --- Getters ---
    public String getMainImage() {return mainImage; }
    public String getImage2() {return image2;}
    public String  getImage3() {return image3;}
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public String getCategory() { return category; }
    public Long getCategoryId() { return categoryId; }
    public BigDecimal getTotalPrice() { return totalPrice; }

    // --- Setters ---
    public void setMainImage(String mainImage) {this.mainImage=mainImage; }
    public void setImage2(String image2) {this.image2=image2; }
    public void setImage3(String image3) {this.image3=image3; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public void setCategory(String category) { this.category = category; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}