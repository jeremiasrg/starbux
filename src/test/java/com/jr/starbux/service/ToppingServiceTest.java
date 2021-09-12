package com.jr.starbux.service;

import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jr.starbux.entity.Topping;

@SpringBootTest
class ToppingServiceTest {

	@Autowired
	private ToppingService service;

	@Test
	void shouldReturnMoreThanOneTopping_WhenFindAllToppings() {
		List<Topping> toppings = service.findAll();
		Assertions.assertTrue(toppings.size() > 1);
	}

	@Test
	void shouldReturnSpecificTopping_WhenFindTopping() throws Exception {
		Topping rt = service.find(1L);
		Assertions.assertEquals("Milk", rt.getName());
	}

	@Test
	void shouldUpdateSpecificTopping_WhenUpdateTopping() throws Exception {
		Topping topping = service.find(1L);
		String originalName = topping.getName();
		topping.setName(originalName + "_test");
		service.update(1L, topping);
		Topping test = service.find(1L);
		Assertions.assertEquals(test.getName(), "Milk" + "_test");
		test.setName(originalName);
		service.update(1L, test);
	}

	@Test
	void shouldCreateTopping_WhenCreateTopping() throws Exception {
		Topping topping = new Topping();

		topping.setName("Topping unit test");
		topping.setPrice(100.00);
		Topping rt = service.save(topping);
		Assertions.assertNotNull(rt.getId());
	}

	@Test
	void shouldDeleteTopping_WhenDeleteTopping() throws Exception {
		service.delete(1L);
		Topping rt = service.find(1L);
		Assertions.assertEquals(true, rt.getActive());
		rt.setActive(true);
		service.update(1L, rt);
	}

}
