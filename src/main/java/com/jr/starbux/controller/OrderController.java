package com.jr.starbux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.model.Order;
import com.jr.starbux.service.OrderService;

@RestController()
@RequestMapping("/order")
public class OrderController implements BaseController<Order, Long> {

	@Autowired
	private OrderService service;

	@Override
	public Order create(Order object) {
		try {
			return	service.save(object);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	public void update(Long id, Order entity) {
		try {
			service.update(id, entity);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	public List<Order> findAll() {
		return service.findAll();
	}

	@Override
	public Order find(Long id) {
		Order entity;
		try {
			entity = service.find(id);
			return entity;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	public void delete(Long id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

}
