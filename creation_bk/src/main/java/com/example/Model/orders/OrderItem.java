package com.example.Model.orders;

import java.math.BigDecimal;

import com.example.Model.products.Products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    private Integer quantity;

    @Column(precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalPrice;

    // --- Constructors ---

    // No-Args Constructor (Required by JPA)
    public OrderItem() {
    }

    // All-Args Constructor (Excluding ID as it is auto-generated)
    public OrderItem(Order order, Products product, Integer quantity, BigDecimal unitPrice, BigDecimal totalPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    // --- Getters ---

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Products getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    // --- Setters ---

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}