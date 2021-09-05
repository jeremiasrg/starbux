package com.jr.starbux.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.model.Drink;
import com.jr.starbux.model.Order;
import com.jr.starbux.model.OrderDrink;
import com.jr.starbux.model.OrderDrinkId;
import com.jr.starbux.repository.DrinkRepository;
import com.jr.starbux.repository.OrderDrinkRepository;
import com.jr.starbux.repository.OrderRepository;

@RestController()
@RequestMapping("/order")
public class OrderController extends BaseController<Order, Long, OrderRepository> {

	@Autowired
	private OrderDrinkRepository odRepository;

	@Autowired
	private DrinkRepository dRepository;

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	@Transactional
	@Override
	public Order create(@RequestBody Order object) {

		Order o = new Order();
		o.setCustomerName(object.getCustomerName());

		super.repository.save(o);

		object.setId(o.getId());
		object.setDateTime(o.getDateTime());

		for (OrderDrink od : object.getDrinks()) {
			OrderDrinkId odi = new OrderDrinkId();
			Optional<Drink> drink = dRepository.findById(od.getDrink().getId()); // TODO
			od.setDrink(drink.get());
			od.setDrinkUnitPrice(od.getDrink().getPrice());
			odi.setDrinkId(od.getDrink().getId());
			
			odi.setOrderId(o.getId());
			od.setId(odi);
		}

		odRepository.saveAllAndFlush(object.getDrinks());
		Optional<Order> a = super.repository.findById(o.getId());

		return object;
	}

}
