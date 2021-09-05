package com.jr.starbux.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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

public abstract class BaseController<T, ID, R extends JpaRepository<T, ID>> {

	@Autowired
	protected R repository;

	@Autowired
	protected ModelMapper modelMapper;

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public T create(@RequestBody T object) {

		return (T) repository.save(object);
	}

	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable ID id, @RequestBody T entity) {
		T objectDb = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, entity.getClass() + " not found"));

		modelMapper.map(entity, objectDb);
		repository.save(objectDb);
	}
	
	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<T> findAll() {
		List<T> rt = repository.findAll();
		return rt;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public T find(@PathVariable("id") ID id) {
		T entity = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));
		return entity;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") ID id) {}
	
}
