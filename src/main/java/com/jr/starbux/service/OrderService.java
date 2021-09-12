package com.jr.starbux.service;

import com.jr.starbux.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jr.starbux.entity.Drink;
import com.jr.starbux.entity.Order;
import com.jr.starbux.entity.OrderDrink;
import com.jr.starbux.entity.OrderDrinkTopping;
import com.jr.starbux.entity.Topping;
import com.jr.starbux.math.Discount;
import com.jr.starbux.repository.OrderRepository;

@Service
public class OrderService extends BaseService<Order, Long, OrderRepository> {

    @Autowired
    DrinkService dService;

    @Autowired
    OrderDrinkService odService;

    @Autowired
    OrderDrinkToppingService odtService;

    @Autowired
    ToppingService tService;

    @Transactional
    @Override
    public Order save(Order object) throws ObjectNotFoundException {
        Order o = new Order();
        o.setClientId(object.getClient().getId());
        o.setAddress(object.getAddress());
        
        o = super.repository.save(o);

        object.setId(o.getId());
        object.setDateTime(o.getDateTime());
        object.setActive(true);
        object.setClientId(object.getClient().getId());
        

        for (OrderDrink od : object.getOrder()) {

            // Create orderDrink
            Drink drink = dService.find(od.getDrink().getId());
            od.setDrink(drink);
            od.setDrinkUnitPrice(od.getDrink().getPrice());
            od.setDrinkId(od.getDrink().getId());
            od.setOrderId(o.getId());
            od = odService.save(od);

            // Create orderDrinkTopping
            for (OrderDrinkTopping t : od.getToppings()) {

                Topping topping = tService.find(t.getTopping().getId());
                t.setDrinkId(od.getDrink().getId());
                t.setOrderDrinkId(od.getId());
                t.setOrderId(o.getId());
                t.setToppingId(t.getTopping().getId());
                t.setToppingUnitPrice(topping.getPrice());
                t.setActive(true);
                t.setTopping(topping);
                odtService.save(t);
            }

        }
        double discount = Discount.getInstance().calcDiscount(object);
        object.setDiscount(discount);
        super.repository.save(object);
        return object;
    }
}
