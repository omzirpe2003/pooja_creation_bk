package com.example.repo.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.orders.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	Optional<Order> findByOrderNumber(String orderNumber);
	Optional<List<Order>> findByUserId(Long userId);
}