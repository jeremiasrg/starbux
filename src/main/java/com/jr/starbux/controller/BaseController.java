package com.jr.starbux.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface BaseController<T, ID> {

	

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public T create(@RequestBody T object);
//	{
//
//		return (T) service.save(object);
//	}

	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable ID id, @RequestBody T entity);
//	{
//		try {
//			service.update(id, entity);
//		} catch (Exception e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//		}
//	}
	
	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<T> findAll();
//	{
//		List<T> rt = service.findAll();
//		return rt;
//	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public T find(@PathVariable("id") ID id);
//	{
//		T entity;
//		try {
//			entity = (T) service.find(id);
//			return entity;
//		} catch (Exception e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//		}
//		
//	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") ID id);
//	{}
	
}
