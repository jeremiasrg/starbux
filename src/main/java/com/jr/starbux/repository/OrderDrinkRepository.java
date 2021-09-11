package com.jr.starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jr.starbux.entity.OrderDrink;

public interface OrderDrinkRepository extends JpaRepository<OrderDrink, Long> {

}
