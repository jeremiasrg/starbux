package com.jr.starbux.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.entity.Drink;
import com.jr.starbux.entity.Topping;
import com.jr.starbux.exceptions.ObjectNotFoundException;
import com.jr.starbux.request.DrinkRequest;
import com.jr.starbux.request.ToppingRequest;
import com.jr.starbux.response.DrinkResponse;
import com.jr.starbux.response.MostUsedToppingsDrinks;
import com.jr.starbux.response.ToppingResponse;
import com.jr.starbux.response.TotalAmountCustomer;
import com.jr.starbux.service.AdminService;
import com.jr.starbux.service.DrinkService;
import com.jr.starbux.service.ToppingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

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

	@GetMapping("/totalAmountCustomer")
	@Operation(summary = "Total amount of the orders per customer.")
//	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public List<TotalAmountCustomer> totalAmountCustomer(String customerName) {
		return service.totalAmountCustomer();
	}

	@GetMapping("/mostUsedToppingsDrinks")
	@Operation(summary = "Most used toppings for drinks.")
//	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public List<MostUsedToppingsDrinks> mostUsedToppingsDrinks() {
		return service.mostUsedToppingsDrinks();
	}

	@Operation(summary = "Deletes a specific topping by Id")
	@DeleteMapping("drink/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void deleteDrink(@PathVariable("id") Long id) throws ObjectNotFoundException {
		log.info("Method delete called");
		try {
			dService.delete(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ObjectNotFoundException();
		}
	}

	@Operation(summary = "Updates a drink")
	@PutMapping("drink/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void updateDrink(@PathVariable("id") Long id, @RequestBody DrinkRequest request)
			throws ObjectNotFoundException {
		log.info("Method update called");
		try {

			Drink entity = new Drink();

			BeanUtils.copyProperties(request, entity);
			dService.update(id, entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ObjectNotFoundException();
		}
	}

	@Operation(summary = "Creates a new drink")
	@PostMapping("/drink")
	@ResponseStatus(code = HttpStatus.CREATED)
//	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public DrinkResponse createDrink(@RequestBody DrinkRequest request) throws Exception {
		log.info("Method create called");

		Drink entity = new Drink();

		BeanUtils.copyProperties(request, entity);
		entity = dService.save(entity);

		DrinkResponse response = new DrinkResponse();
		BeanUtils.copyProperties(entity, response);
		return response;

	}

	@Operation(summary = "Deletes a specific topping by Id")
	@DeleteMapping("topping/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void deleteTopping(@PathVariable("id") Long id) throws ObjectNotFoundException {
		log.info("Method delete called");
		try {
			tpService.delete(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ObjectNotFoundException();
		}
	}

	@Operation(summary = "Updates a topping")
	@PutMapping("topping/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void updateTopping(@PathVariable("id") Long id, @RequestBody ToppingRequest request) throws ObjectNotFoundException {
		log.info("Method update called");
		try {

			Topping entity = new Topping();

			BeanUtils.copyProperties(request, entity);
			tpService.update(id, entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ObjectNotFoundException();
		}
	}

	@Operation(summary = "Creates a new topping")
	@PostMapping("/topping")
	@ResponseStatus(code = HttpStatus.CREATED)
//	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public ToppingResponse createTopping(@RequestBody ToppingRequest request) throws ObjectNotFoundException {
		log.info("Method create called");

			Topping entity = new Topping();

			BeanUtils.copyProperties(request, entity);
			entity = tpService.save(entity);

			ToppingResponse response = new ToppingResponse();
			BeanUtils.copyProperties(entity, response);
			return response;


	}
}