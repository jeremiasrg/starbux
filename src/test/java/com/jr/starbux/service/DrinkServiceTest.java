package com.jr.starbux.service;


import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jr.starbux.entity.Drink;

	
@SpringBootTest
public class DrinkServiceTest {

	@Autowired
	private DrinkService service;
	

	@Test
	public void shouldReturnMoreThanOneDrink_WhenFindAllDrinks() throws Exception {
		List<Drink> drinks = service.findAll();
		Assertions.assertTrue(drinks.size() > 1);
	}
	
	@Test
	public void shouldReturnSpecificDrink_WhenFindDrink() throws Exception{
		Drink rt = service.find(1L);
		Assertions.assertEquals( rt.getName(),"Black Coffee");
	}
	
	@Test
	public void shouldUpdateSpecificDrink_WhenUpdateDrink() throws Exception{
		Drink drink = service.find(1L);
		String originalName = drink.getName();
		drink.setName(originalName + "_test");
		service.update(1L, drink);
		Drink test = service.find(1L);
		Assertions.assertEquals( test.getName(), "Black Coffee" + "_test");
		test.setName(originalName);
		service.update(1L, test);
	}
	
	@Test
	public void shouldCreateDrink_WhenCreateDrink() throws Exception{
		Drink drink = new Drink();
		
		drink.setName("Drink unit test");
		drink.setPrice(100.00);
		Drink rt = service.save(drink);
		Assertions.assertTrue(rt.getId() != null);
	}
	
	@Test
	public void shouldDeleteDrink_WhenDeleteDrink() throws Exception{
		service.delete(1L);
		Drink rt = service.find(1L);
		Assertions.assertTrue(rt.getActive() == false);
		rt.setActive(true);
		service.update(1L, rt);
	}

}
