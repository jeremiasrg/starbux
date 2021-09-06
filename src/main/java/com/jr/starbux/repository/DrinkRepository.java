package com.jr.starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jr.starbux.model.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

//	@Query("select d from Drink d where d.active = true")
//	public List<Drink> findAllActivedDrinks();

}