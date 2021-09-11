package com.jr.starbux.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.jr.starbux.entity.BaseEntity;
import com.jr.starbux.generic.Generic;

@Service
public abstract class BaseService<T extends BaseEntity, I, R extends JpaRepository<T, I>> {

	@Autowired
	protected R repository;

	@Autowired
	protected ModelMapper modelMapper;

	public T save(T object) throws Exception {

		T r = repository.save(object);
		repository.flush();
		return r;
	}

	public void update(I id, T entity) throws Exception {
		T objectDb = repository.findById(id).orElseThrow(() -> new Exception(entity.getClass() + " not found"));

		modelMapper.map(entity, objectDb);
		repository.save(objectDb);
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public T find(I id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("Object not found"));
		
	}

	public void delete(I id) throws Exception {
		T entity = repository.findById(id).orElseThrow(() -> new Exception("Drink not found"));

		repository.save(entity);
	}

}
