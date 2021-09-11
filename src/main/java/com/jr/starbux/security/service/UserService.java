package com.jr.starbux.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.starbux.security.entity.User;
import com.jr.starbux.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	
	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public void save(User user) {
        repository.save(user);
    }
}
