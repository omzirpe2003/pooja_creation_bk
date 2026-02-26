package com.example.Model.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.example.enums.Orders.OrderStatus;

public class OrderResponse {

    private String username;
    private Long userId;
    private Long orderId;
    private String orderNumber;
    private OrderStatus status; 
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;
    
    // New Fields
    private String emailId;
    private String mobileNo;

    // No-Args Constructor
    public OrderResponse() {
    }

    // Updated All-Args Constructor
    public OrderResponse(String username, Long userId, Long orderId, String orderNumber, 
                         OrderStatus status, BigDecimal totalAmount, LocalDateTime createdAt, 
                         List<OrderItemResponse> items, String emailId, String mobileNo) {
        this.username = username;
        this.userId = userId;
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.items = items;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }

    // --- New Getters and Setters ---

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    // --- Existing Getters and Setters ---

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserName(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}