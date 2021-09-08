package com.jr.starbux.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.model.Topping;
import com.jr.starbux.repository.ToppingRepository;
import com.jr.starbux.service.ToppingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Topping service")
@RestController
@RequestMapping("/topping")
public class ToppingController extends BaseController<Topping, Long, ToppingRepository, ToppingService> {

	@Operation(summary = "Creates a new topping")
	@Override
	public Topping create(@RequestBody Topping object) {
		return super.create(object);
	}

	@Operation(summary = "Updates a topping")
	@Override
	public void update(@PathVariable Long id, @RequestBody Topping entity) {
		super.update(id, entity);
	}

	@Operation(summary = "Lists all toppings")
	@Override
	public List<Topping> findAll() {
		return super.findAll();
	}

	@Operation(summary = "Finds a specific topping by Id")
	@Override
	public Topping find(@PathVariable Long id) {
		return super.find(id);
	}

	@Operation(summary = "Deletes a specific topping by Id")
	@Override
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}

}
