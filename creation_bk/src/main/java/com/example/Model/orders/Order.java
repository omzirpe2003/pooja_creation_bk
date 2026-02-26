

package com.example.Model.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.example.Model.authmodel.UserModel;
import com.example.enums.Orders.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",updatable = false)
    private UserModel user;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    // --- Lifecycle Hooks ---
    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // --- Constructors ---
    public Order() {
    }

    public Order(Long id, String orderNumber, OrderStatus status, BigDecimal totalAmount, 
                 LocalDateTime createdAt, LocalDateTime updatedAt, List<OrderItem> items) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items;
    }

    // --- Helper Method for Bidirectional Relationship ---
    public void addOrderItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }

    // --- Getters ---
    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public List<OrderItem> getItems() { return items; }
    public UserModel getUser() {return user ;} 

    // --- Setters ---
    public void setUser(UserModel user) {this.user=user; }
    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}