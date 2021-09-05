package com.jr.starbux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.model.Drink;
import com.jr.starbux.repository.DrinkRepository;

@RestController
@RequestMapping("drink")
public class DrinkController extends BaseController<Drink, Long,  DrinkRepository> {

	@Override
	public void delete(Long id) {
		Drink drink = super.repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drink not found"));

		drink.setActive(false);
		repository.save(drink);
	}
}