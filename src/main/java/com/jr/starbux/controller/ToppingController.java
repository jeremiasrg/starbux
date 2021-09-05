package com.jr.starbux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.model.Topping;
import com.jr.starbux.service.ToppingService;

@RestController
@RequestMapping("/topping")
public class ToppingController implements BaseController<Topping, Long> {

	@Autowired
	private ToppingService service;
	
	@Override
	public Topping create(Topping object) {
		try {
			return service.save(object);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	public void update(Long id, Topping entity) {
		try {
			service.update(id, entity);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	public List<Topping> findAll() {
		return service.findAll();
	}

	@Override
	public Topping find(Long id) {
		Topping entity;
		try {
			entity =  service.find(id);
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
