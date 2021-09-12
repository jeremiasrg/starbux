package com.jr.starbux.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jr.starbux.entity.BaseEntity;
import com.jr.starbux.exceptions.ObjectNotFoundException;
import com.jr.starbux.service.BaseService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseViewAndCreateController<E extends BaseEntity, I, S extends BaseService, R, P> {

	// (E) - Entity
	// (I) - ID
	// (S) - Service
	// (R) - Request
	// (P) - Response

	@Autowired
	private S service;

	@Autowired
	protected ModelMapper modelMapper;

	private Class<E> entityType;
	private Class<P> responseType;

	protected BaseViewAndCreateController(Class<E> entityType, Class<P> responseType) {
		this.entityType = entityType;
		this.responseType = responseType;
	}

	@PostMapping("/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public P create(@RequestBody R request) throws Exception {
		log.info("Method create called");

		E entity = modelMapper.map(request, this.entityType);
		entity = (E) service.save(entity);
		return modelMapper.map(entity, this.responseType);
	}

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<P> findAll() {
		log.info("Method findAll called");

		List<E> listEntity = service.findAll();

		return listEntity.stream().map(o -> modelMapper.map(o, this.responseType)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public P find(@PathVariable("id") I id) throws ObjectNotFoundException {
		log.info("Method find called");
		E entity;
		entity = (E) service.find(id);
		return modelMapper.map(entity, this.responseType);
	}

}
