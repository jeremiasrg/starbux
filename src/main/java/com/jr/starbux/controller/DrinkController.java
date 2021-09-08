package com.jr.starbux.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.model.Drink;
import com.jr.starbux.repository.DrinkRepository;
import com.jr.starbux.service.DrinkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Drink service")
@RestController
@RequestMapping("/drink")
public class DrinkController extends BaseController<Drink, Long, DrinkRepository, DrinkService> {

	@Operation(summary = "Creates a new drink")
	@Override
	public Drink create(@RequestBody Drink object) {
		return super.create(object);
	}

	@Operation(summary = "Updates a drink")
	@Override
	public void update(@PathVariable Long id, @RequestBody Drink entity) {
		super.update(id, entity);
	}

	@Operation(summary = "Lists all drinks")
	@Override
	public List<Drink> findAll() {
		return super.findAll();
	}

	@Operation(summary = "Finds a specific drink by Id")
	@Override
	public Drink find(@PathVariable Long id) {
		return super.find(id);
	}

	@Operation(summary = "Deletes a specific drink by Id")
	@Override
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}
	
	
	

}