package com.jr.starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jr.starbux.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
