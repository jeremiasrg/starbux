package com.jr.starbux.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jr.starbux.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
