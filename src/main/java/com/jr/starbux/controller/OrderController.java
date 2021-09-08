package com.jr.starbux.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.model.Order;
import com.jr.starbux.repository.OrderRepository;
import com.jr.starbux.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order service")
@RestController()
@RequestMapping("/order")
public class OrderController extends BaseController<Order, Long, OrderRepository, OrderService> {

//	@Operation(summary = "Creates a new order")
//	@Override
//	public Order create(@RequestBody Order object) {
//		return super.create(object);
//	}

	@Operation(summary = "Updates a order")
	@Override
	public void update(@PathVariable Long id, @RequestBody Order entity) {
		super.update(id, entity);
	}

	@Operation(summary = "Lists all orders")
	@Override
	public List<Order> findAll() {
		return super.findAll();
	}

	@Operation(summary = "Finds a specific order by Id")
	@Override
	public Order find(@PathVariable Long id) {
		return super.find(id);
	}

	@Operation(summary = "Deletes a specific order by Id")
	@Override
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}

}
