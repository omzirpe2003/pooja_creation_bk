


package com.example.Model.orders;


public class OrderItemRequest {

    private Long productId;
    private Integer quantity;

    // No-Args Constructor
    public OrderItemRequest() {
    }

    // All-Args Constructor
    public OrderItemRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters
    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    // Setters
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}