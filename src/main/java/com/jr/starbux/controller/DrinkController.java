package com.jr.starbux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.model.Drink;
import com.jr.starbux.service.DrinkService;

@RestController
@RequestMapping("drink")
public class DrinkController implements BaseController<Drink, Long> {

	@Autowired
	private DrinkService service;
	
	@Override
	public Drink create(Drink object) {
		try {
			return service.save(object);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	public void update(Long id, Drink entity) {
		try {
			service.update(id, entity);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	public List<Drink> findAll() {
		return service.findAll();
	}

	@Override
	public Drink find(Long id) {
		Drink entity;
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