package com.jr.starbux.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.jr.starbux.model.OrderDrink;
import com.jr.starbux.repository.OrderDrinkRepository;

@Service
public class OrderDrinkService extends BaseService<OrderDrink, Long, OrderDrinkRepository> {

	public void saveAllAndFlush(Set<OrderDrink> drinks) {
		
		super.repository.saveAllAndFlush(drinks);
		
	}

}
