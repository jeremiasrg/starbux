package com.jr.starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jr.starbux.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
