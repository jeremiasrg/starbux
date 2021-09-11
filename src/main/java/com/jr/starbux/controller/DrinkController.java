package com.jr.starbux.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.entity.Drink;
import com.jr.starbux.service.DrinkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Drink service")
@RestController
@RequestMapping("/drink")
public class DrinkController extends BaseViewController<Drink, Long, DrinkService> {


	@Operation(summary = "Lists all drinks")
	@Override
	public List findAll() {
		return super.findAll();
	}

	@Operation(summary = "Finds a specific drink by Id")
	@Override
	public Drink find(@PathVariable Long id) {
		return super.find(id);
	}	

}