package com.ecomm.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByOrderId(String orderId);
}
