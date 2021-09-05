package com.jr.starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jr.starbux.model.OrderDrinkTopping;
import com.jr.starbux.model.OrderDrinkToppingId;

public interface OrderDrinkToppingRepository extends JpaRepository<OrderDrinkTopping, OrderDrinkToppingId> {

}
