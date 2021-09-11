package com.jr.starbux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.entity.BaseModel;
import com.jr.starbux.service.BaseService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseViewController<T extends BaseModel, I, S extends BaseService > {

	@Autowired
	private S service;
	
	

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<T> findAll(){
		log.info("Method findAll called");
		return service.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public T find(@PathVariable("id") I id) {
		log.info("Method find called");
		T entity;
		try {
			entity =  (T) service.find(id);
			if(entity == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found ");	
			}
			return entity;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
