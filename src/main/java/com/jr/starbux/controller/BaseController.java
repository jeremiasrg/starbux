package com.jr.starbux.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.model.BaseModel;
import com.jr.starbux.service.BaseService;

public abstract class BaseController<T extends BaseModel, ID, R extends JpaRepository<T, ID>, S extends BaseService > {

	@Autowired
	private S service;
	
	Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public T create(@RequestBody T object) {
		logger.info("Method create called");
		
		try {
			return (T) service.save(object);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping(value= "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable ID id, @RequestBody T entity) {
		logger.info("Method update called");
		try {
			service.update(id, entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<T> findAll(){
		logger.info("Method findAll called");
		return service.findAll();
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public T find(@PathVariable("id") ID id) {
		logger.info("Method find called");
		T entity;
		try {
			entity =  (T) service.find(id);
			return entity;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") ID id) {
		logger.info("Method delete called");
		try {
			service.delete(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
