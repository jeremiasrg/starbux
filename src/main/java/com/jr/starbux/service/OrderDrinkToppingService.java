package com.jr.starbux.service;

import org.springframework.stereotype.Service;

import com.jr.starbux.entity.OrderDrinkTopping;
import com.jr.starbux.repository.OrderDrinkToppingRepository;

@Service
public class OrderDrinkToppingService  extends BaseService<OrderDrinkTopping, Long, OrderDrinkToppingRepository> {

}
