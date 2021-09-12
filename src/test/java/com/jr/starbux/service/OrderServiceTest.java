package com.jr.starbux.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jr.starbux.entity.Client;
import com.jr.starbux.entity.Drink;
import com.jr.starbux.entity.OrderDrink;
import com.jr.starbux.entity.OrderDrinkTopping;
import com.jr.starbux.entity.Topping;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

	@Autowired
	private OrderService service;

	@Test
	@Order(1)
	void shouldCreateOrder() throws Exception {

		// Client
		Client cli = new Client();
		cli.setId(1L);

		// Order
		com.jr.starbux.entity.Order order = new com.jr.starbux.entity.Order();
		order.setClient(cli);
		order.setAddress("My test address");
		order.setClientId(cli.getId());

		// Order Drink
		OrderDrink orderDrink = new OrderDrink();
		orderDrink.setDrink(new Drink(2L, null, null));

		// Order Drink Topping
		OrderDrinkTopping odt = new OrderDrinkTopping();

		// Drink 1
		odt.setTopping(new Topping(2L, null, null));
		List<OrderDrink> listOrderDrink = new ArrayList<>();
		List<OrderDrinkTopping> listOrderDrinkTopping = new ArrayList<>();
		listOrderDrinkTopping.add(odt);
		listOrderDrink.add(orderDrink);
		listOrderDrink.get(0).setToppings(listOrderDrinkTopping);

		// Drink 2
		odt.setTopping(new Topping(3L, null, null));
		listOrderDrinkTopping.add(odt);
		listOrderDrink.add(orderDrink);
		listOrderDrink.get(1).setToppings(listOrderDrinkTopping);

		// Drink 3
		odt.setTopping(new Topping(4L, null, null));
		listOrderDrinkTopping.add(odt);
		listOrderDrink.add(orderDrink);
		listOrderDrink.get(2).setToppings(listOrderDrinkTopping);

		// Finalize object
		order.setOrder(listOrderDrink);

		// save
		com.jr.starbux.entity.Order rt = service.save(order);
		Assertions.assertNotNull(rt.getId());
	}

	@Test
	@Order(2)
	void shouldReturnMoreThanOneOrder() {
		List<com.jr.starbux.entity.Order> orders = service.findAll();
		Assertions.assertTrue(orders.size() >= 1);
	}

	@Test
	@Order(3)
	void shouldReturnSpecificOrder() throws Exception {
		com.jr.starbux.entity.Order rt = service.find(1L);
		Assertions.assertEquals("Mr Tests", rt.getClient().getName());
	}
}
