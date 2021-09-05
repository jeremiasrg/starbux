package com.jr.starbux.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.starbux.model.Drink;
import com.jr.starbux.model.Order;
import com.jr.starbux.model.OrderDrink;
import com.jr.starbux.model.OrderDrinkId;
import com.jr.starbux.repository.OrderRepository;

@Service
public class OrderService extends BaseService<Order, Long, OrderRepository>{

	@Autowired
	DrinkService dService;
	
	@Autowired
	OrderDrinkService odService;
	
	@Override
	public Order save(Order object) throws Exception {
		Order o = new Order();
		o.setCustomerName(object.getCustomerName());

		super.repository.save(o);

		object.setId(o.getId());
		object.setDateTime(o.getDateTime());

		for (OrderDrink od : object.getDrinks()) {
			OrderDrinkId odi = new OrderDrinkId();
			Drink drink = dService.find(od.getDrink().getId()); // TODO
			od.setDrink(drink);
			od.setDrinkUnitPrice(od.getDrink().getPrice());
			odi.setDrinkId(od.getDrink().getId());
			
			odi.setOrderId(o.getId());
			od.setId(odi);
		}

		//Order drink Service
		odService.saveAllAndFlush(object.getDrinks());

		return object;
//		return super.save(object);
	}
	
	

}
