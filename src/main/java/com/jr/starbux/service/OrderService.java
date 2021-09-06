package com.jr.starbux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jr.starbux.math.Discount;
import com.jr.starbux.model.Drink;
import com.jr.starbux.model.Order;
import com.jr.starbux.model.OrderDrink;
import com.jr.starbux.model.OrderDrinkTopping;
import com.jr.starbux.model.Topping;
import com.jr.starbux.repository.OrderRepository;

@Service
public class OrderService extends BaseService<Order, Long, OrderRepository> {

	@Autowired
	DrinkService dService;

	@Autowired
	ToppingService tService;

	@Transactional
	@Override
	public Order save(Order object) throws Exception {
		Order o = new Order();
		o.setCustomerName(object.getCustomerName());

		super.repository.save(o);

		object.setId(o.getId());
		object.setDateTime(o.getDateTime());

		for (OrderDrink od : object.getOrder()) {

			// Create orderDrink
			Drink drink = dService.find(od.getDrink().getId()); // TODO
			od.setDrink(drink);
			od.setDrinkUnitPrice(od.getDrink().getPrice());
			od.setDrinkId(od.getDrink().getId());
			od.setOrderId(o.getId());

			// Create orderDrinkTopping
			for (OrderDrinkTopping t : od.getToppings()) {
				OrderDrinkTopping odt = new OrderDrinkTopping();
				t.setDrinkId(od.getDrink().getId());
				t.setOrderId(o.getId());
				t.setToppingId(t.getTopping().getId());
				Topping topping = tService.find(t.getTopping().getId());
				t.setToppingUnitPrice(topping.getPrice());
				t.getTopping().setName(topping.getName());
				t.getTopping().setPrice(topping.getPrice());
				t.getTopping().setActive(topping.getActive());
			}
		}

		double discount = Discount.getInstance().calcDiscount(object);
		object.setDiscount(discount);
		// Order drink Service
		super.repository.save(object);

		return object;
	}

}
