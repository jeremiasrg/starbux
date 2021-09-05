package com.jr.starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jr.starbux.model.OrderDrink;
import com.jr.starbux.model.OrderDrinkId;

public interface OrderDrinkRepository extends JpaRepository<OrderDrink, OrderDrinkId> {

}
