package com.jr.starbux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.model.Topping;
import com.jr.starbux.repository.ToppingRepository;

@RestController
@RequestMapping("/topping")
public class ToppingController extends BaseController<Topping, Long,  ToppingRepository> {

	@Override
	public void delete(Long id) {
		Topping topping = super.repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topping not found"));

		topping.setActive(false);
		repository.save(topping);
	}
	


}
