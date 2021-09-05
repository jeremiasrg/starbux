package com.jr.starbux.service;

import org.springframework.stereotype.Service;

import com.jr.starbux.model.Drink;
import com.jr.starbux.repository.DrinkRepository;

@Service
public class DrinkService extends BaseService<Drink, Long, DrinkRepository> {

	@Override
	public void delete(Long id) throws Exception {
		Drink drink = super.repository.findById(id)
				.orElseThrow(() -> new Exception("Drink not found"));

		drink.setActive(false);
		repository.save(drink);
	}
}
