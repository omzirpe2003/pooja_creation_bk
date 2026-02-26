package com.example.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.Model.orders.OrderItem;



public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderItem o WHERE o.product.id = :productId")
    void deleteByProductId(@Param("productId") Long productId);
}