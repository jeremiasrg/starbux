package com.jr.starbux.service;

import java.util.List;


import com.jr.starbux.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jr.starbux.entity.Topping;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ToppingServiceTest {

	@Autowired
	private ToppingService service;

	@Test
	@Order(1)
	void shouldReturnMoreThanOneTopping() {
		List<Topping> toppings = service.findAll();
		Assertions.assertTrue(toppings.size() > 1);
	}

	@Test
	@Order(2)
	void shouldReturnSpecificTopping() throws Exception {
		Topping rt = service.find(1L);
		Assertions.assertEquals("Milk", rt.getName());
	}

	@Test
	@Order(3)
	void shouldUpdateSpecificTopping() throws Exception {
		Topping topping = service.find(1L);
		String originalName = topping.getName();
		topping.setName(originalName + "_test");
		service.update(1L, topping);
		Topping test = service.find(1L);
		Assertions.assertEquals(test.getName(), "Milk" + "_test");
	}

	@Test
	@Order(4)
	void shouldCreateTopping() throws Exception {
		Topping topping = new Topping();
		topping.setName("Topping unit test");
		topping.setPrice(100.00);
		Topping rt = service.save(topping);
		Assertions.assertNotNull(rt.getId());
	}

	@Test
	@Order(5)
	void shouldDeleteTopping() throws Exception {
		service.delete(1L);
		Assertions.assertThrows(ObjectNotFoundException.class, () -> service.find(1L));
	}

}
