package com.jr.starbux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.model.Drink;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin api")
@RestController
@RequestMapping("/laranja")
public class AdminController {
	
	@GetMapping(path = "/totalAmountCustomer")
	@Operation(summary = "Total amount of the orders per customer.")
	public String totalAmountCustomer() {
		
		return null;
	}
	
	@GetMapping(path = "/mostUsedToppingsDrinks")
	@Operation(summary = "Most used toppings for drinks.")
	public String mostUsedToppingsDrinks() {
		return null;
	}

}
