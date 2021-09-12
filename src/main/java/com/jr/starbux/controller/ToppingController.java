package com.jr.starbux.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.entity.Topping;
import com.jr.starbux.exceptions.ObjectNotFoundException;
import com.jr.starbux.request.ToppingRequest;
import com.jr.starbux.response.ToppingResponse;
import com.jr.starbux.service.ToppingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Topping service")
@RestController
@RequestMapping("/topping")
public class ToppingController
		extends BaseViewController<Topping, Long, ToppingService, ToppingRequest, ToppingResponse> {

	protected ToppingController() {
		super(ToppingResponse.class);
	}

	@Operation(summary = "Lists all toppings")
	@Override
	public List<ToppingResponse> findAll() {
		return super.findAll();
	}

	@Operation(summary = "Finds a specific topping by Id")
	@Override
	public ToppingResponse find(@PathVariable Long id) throws ObjectNotFoundException {
		return super.find(id);
	}

}
