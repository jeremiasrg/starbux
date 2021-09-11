package com.jr.starbux.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jr.starbux.entity.Client;
import com.jr.starbux.entity.Drink;
import com.jr.starbux.entity.Order;
import com.jr.starbux.entity.OrderDrink;
import com.jr.starbux.entity.OrderDrinkTopping;
import com.jr.starbux.entity.Topping;

@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private OrderService service;

	@Test
	@org.junit.jupiter.api.Order(1)
	public void shouldCreateOrder_WhenCreateOrder() throws Exception {

		// Client
		Client cli = new Client();
		cli.setId(1L);

		// Order
		Order order = new Order();
		order.setClient(cli);

		// Order Drink
		OrderDrink orderDrink = new OrderDrink();
		orderDrink.setDrink(new Drink(1L, null, null));

		// Order Drink Topping
		OrderDrinkTopping odt = new OrderDrinkTopping();

		// Drink 1
		odt.setTopping(new Topping(1L, null, null));
		List<OrderDrink> listOrderDrink = new ArrayList<>();
		List<OrderDrinkTopping> listOrderDrinkTopping = new ArrayList<>();
		listOrderDrink.add(orderDrink);
		listOrderDrink.get(0).setToppings(listOrderDrinkTopping);

		// Drink 2
		odt.setTopping(new Topping(1L, null, null));
		listOrderDrink.add(orderDrink);
		listOrderDrink.get(1).setToppings(listOrderDrinkTopping);

		// Drink 3
		odt.setTopping(new Topping(1L, null, null));
		listOrderDrink.add(orderDrink);
		listOrderDrink.get(2).setToppings(listOrderDrinkTopping);

		// Finalize object
		order.setOrder(listOrderDrink);

		// save
		Order rt = service.save(order);
		Assertions.assertTrue(rt.getId() != null);
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	public void shouldReturnMoreThanOneOrder_WhenFindAllOrders() throws Exception {
		List<Order> orders = service.findAll();
		Assertions.assertTrue(orders.size() >= 1);
	}

	@Test
	@org.junit.jupiter.api.Order(3)
	public void shouldReturnSpecificOrder_WhenFindOrder() throws Exception {
		Order rt = service.find(1L);
		Assertions.assertEquals(rt.getClient().getName(), "Customer unit test");
	}
}
