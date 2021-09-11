package com.jr.starbux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jr.starbux.entity.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

	@Query("select d from Drink d where d.active = true")
	public List<Drink> findAllActivedDrinks();

}