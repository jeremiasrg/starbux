package com.jr.starbux.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.entity.Drink;
import com.jr.starbux.exceptions.ObjectNotFoundException;
import com.jr.starbux.request.DrinkRequest;
import com.jr.starbux.response.DrinkResponse;
import com.jr.starbux.service.DrinkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Drink service")
@RestController
@RequestMapping("/drink")
public class DrinkController extends BaseViewController<Drink, Long, DrinkService, DrinkRequest, DrinkResponse> {

	protected DrinkController() {
		super(DrinkResponse.class);
	}

	@Operation(summary = "Lists all drinks")
	@Override
	public List<DrinkResponse> findAll() {
		return super.findAll();
	}

	@Operation(summary = "Finds a specific drink by Id")
	@Override
	public DrinkResponse find(@PathVariable Long id) throws ObjectNotFoundException {
		return super.find(id);
	}

}