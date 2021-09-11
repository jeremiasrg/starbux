package com.jr.starbux.controller;

import java.util.List;

import com.jr.starbux.service.DrinkService;
import com.jr.starbux.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jr.starbux.entity.Drink;
import com.jr.starbux.entity.Topping;
import com.jr.starbux.response.MostUsedToppingsDrinks;
import com.jr.starbux.response.TotalAmountCustomer;
import com.jr.starbux.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.server.ResponseStatusException;

@Log4j2
@Tag(name = "Admin api")
@RestController
@RequestMapping("/admin")
public class AdminController {

	

	@Autowired
	private AdminService service;

	@Autowired
	private DrinkService dService;

	@Autowired
	private ToppingService tpService;

	@GetMapping(path = "/totalAmountCustomer")
	@Operation(summary = "Total amount of the orders per customer.")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public List<TotalAmountCustomer> totalAmountCustomer(String customerName) {
		return service.totalAmountCustomer(customerName);
	}

	@GetMapping(path = "/mostUsedToppingsDrinks")
	@Operation(summary = "Most used toppings for drinks.")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public List<MostUsedToppingsDrinks> mostUsedToppingsDrinks() {
		return service.mostUsedToppingsDrinks();
	}

	@Operation(summary = "Deletes a specific topping by Id")
	@DeleteMapping("drink/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void deleteDrink(@PathVariable("id") Long id) {
		log.info("Method delete called");
		try {
			dService.delete(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Updates a drink")
	@PutMapping("drink/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void updateDrink(@PathVariable("id") Long id, @RequestBody Drink entity) {
		log.info("Method update called");
		try {
			dService.update(id, entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Creates a new drink")
	@PostMapping("/drink")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public Drink createDrink(@RequestBody Drink object) {
		log.info("Method create called");

		try {
			return dService.save(object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Deletes a specific topping by Id")
	@DeleteMapping("topping/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void deleteTopping(@PathVariable("id") Long id) {
		log.info("Method delete called");
		try {
			tpService.delete(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Updates a topping")
	@PutMapping("topping/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void updateTopping(@PathVariable("id") Long id, @RequestBody Topping entity) {
		log.info("Method update called");
		try {
			tpService.update(id, entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Creates a new topping")
	@PostMapping("/topping")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public Topping createTopping(@RequestBody Topping object) {
		log.info("Method create called");

		try {
			return tpService.save(object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
