package com.jr.starbux.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.entity.Order;
import com.jr.starbux.request.OrderRequest;
import com.jr.starbux.response.OrderResponse;
import com.jr.starbux.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order service")
@RestController()
@RequestMapping("/order")
public class OrderController extends BaseViewAndCreateController<Order, Long, OrderService, OrderRequest, OrderResponse> {

	protected OrderController() {
		super(Order.class, OrderResponse.class);
	}

	@Operation(summary = "Creates a new order")
	@Override
	public OrderResponse create(@RequestBody OrderRequest object) {
		return super.create(object);
	}

	@Operation(summary = "Lists all orders")
	@Override
	public List<OrderResponse> findAll() {
		return super.findAll();
	}

	@Operation(summary = "Finds a specific order by Id")
	@Override
	public OrderResponse find(@PathVariable Long id) {
		return super.find(id);
	}

}
