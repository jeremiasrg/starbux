package com.jr.starbux.service;

import java.util.List;

import com.jr.starbux.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.starbux.entity.BaseEntity;
import com.jr.starbux.repository.BaseRepository;

@Service
public abstract class BaseService<E extends BaseEntity, I, R extends BaseRepository<E, I>> {

	@Autowired
	protected R repository;

	@Autowired
	protected ModelMapper modelMapper;

	public E save(E object) throws ObjectNotFoundException {
		E r = repository.save(object);
		repository.flush();
		return r;
	}

	public void update(I id, E entity) throws ObjectNotFoundException {
		E objectDb = repository.findById(id).orElseThrow(ObjectNotFoundException::new);

		modelMapper.map(entity, objectDb);
		repository.save(objectDb);
	}

	public List<E> findAll() {
		return repository.findAllActived();
	}

	public E find(I id) throws ObjectNotFoundException {
		E entity = repository.findByIdActived(id);
		if (entity == null)
			throw new ObjectNotFoundException();

		return entity;
	}

	public void delete(I id) throws ObjectNotFoundException {
		E entity = repository.findById(id).orElseThrow(ObjectNotFoundException::new);
		entity.setActive(false);
		repository.save(entity);
	}

}
