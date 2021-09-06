package com.jr.starbux.service;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.jr.starbux.model.CommunField;

@Service
public abstract class BaseService<T extends CommunField, ID, R extends JpaRepository<T, ID>> {

	@Autowired
	protected R repository;

	@Autowired
	protected ModelMapper modelMapper;

	public T save(T object) throws Exception{

		return (T) repository.save(object);
	}

	public void update(ID id, T entity) throws Exception {
		T objectDb = repository.findById(id)
				.orElseThrow(() -> new Exception(entity.getClass() + " not found"));

		modelMapper.map(entity, objectDb);
		repository.save(objectDb);
	}
	
	public List<T> findAll() {
		List<T> rt = repository.findAll();
		return rt;
	}
	
	public T find(ID id) throws Exception {
		T entity = repository.findById(id)
				.orElseThrow(() -> new Exception( "Object not found"));
		return entity;
	}
	
	
	public void delete(ID id) throws Exception {
		T entity = repository.findById(id)
				.orElseThrow(() -> new Exception("Drink not found"));

		entity.setActive(false);
		repository.save(entity);
	}
	
}
