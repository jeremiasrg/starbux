package com.jr.starbux.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.entity.BaseEntity;
import com.jr.starbux.generic.Generic;
import com.jr.starbux.service.BaseService;
import com.jr.starbux.utils.ConverterUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseViewAndCreateController<E extends BaseEntity, ID, S extends BaseService, REQ, RES> {

	// (E) - Entity
	// (ID) - ID
	// (S) - Service
	// (REQ) - Request
	// (RES) - Response

	@Autowired
	private S service;

	private Class<E> entityType;
	private Class<RES> responseType;

	protected BaseViewAndCreateController(Class<E> entityType, Class<RES> responseType) {
		this.entityType = entityType;
		this.responseType = responseType;
	}

	@PostMapping("/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RES create(@RequestBody REQ request) {
		log.info("Method create called");
		try {
			Generic<E> gEntity = new Generic<E>(this.entityType);
			E entity = gEntity.newInstance();

			BeanUtils.copyProperties(request, entity);
			entity = (E) service.save(entity);

			Generic<RES> gResponse = new Generic<RES>(this.responseType);
			RES response = gResponse.newInstance();

			BeanUtils.copyProperties(entity, response);
			return response;
		} catch (Exception e) {

			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<RES> findAll() {
		log.info("Method findAll called");

		List<E> listEntity = service.findAll();

		List<RES> response = new ArrayList<RES>();

		BeanUtils.copyProperties(listEntity, response);

		return new ConverterUtils(entityType, responseType).copyPropertiesFromList(listEntity, response, responseType);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public RES find(@PathVariable("id") ID id) {
		log.info("Method find called");
		E entity;
		try {
			entity = (E) service.find(id);
			if (entity == null)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");

			Generic<RES> gResponse = new Generic<RES>(this.responseType);
			RES response = gResponse.newInstance();
			BeanUtils.copyProperties(entity, response);

			return response;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
