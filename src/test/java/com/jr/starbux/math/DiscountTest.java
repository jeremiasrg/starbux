package com.jr.starbux.math;

import com.jr.starbux.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DiscountTest {


    @Test
    void shouldReturnDiscount()  {
        Order order = new Order();
        order.setOrder(generateOrderDrinks(1,10));

        double rt = Discount.getInstance().calcDiscount(order);
        Assertions.assertTrue(rt == 0.0);
    }

    @Test
    void shouldReturn25PercentOfDiscountWithNoToppings()  {

        Order order = new Order();
        // 2 Drinks per 10 euros each
        // 25% of 20 euros = 5 euros
        order.setOrder(generateOrderDrinks(2,10));

        double rt = Discount.getInstance().calcDiscount(order);
        Assertions.assertTrue(rt == 5.0);
    }

    @Test
    void shouldReturnDiscountTheLowestAmountWithNoToppings()  {

        Order order = new Order();
        order.getOrder().addAll(generateOrderDrinks(2,1.21));
        order.getOrder().addAll(generateOrderDrinks(1,1.20));
        order.getOrder().addAll(generateOrderDrinks(2,1.34));

        double rt = Discount.getInstance().calcDiscount(order);
        Assertions.assertTrue(rt == 1.20);
    }


    @Test
    void shouldReturn25PercentOfDiscountWithToppings()  {

        Order order = new Order();
        // 2 Drinks per 10 euros each
        // Each drink has 1 topping 10 euros.
        // Total = 40 euros
        // 25% of 40 euros = 10 euros
        List<OrderDrink> listOrderDrinks = generateOrderDrinks(2, 10);
        List<OrderDrinkTopping> listOrderDrinkToppings = generateOrderDrinkToppings(1, 10);
        listOrderDrinks.get(0).setToppings(listOrderDrinkToppings);
        listOrderDrinks.get(1).setToppings(listOrderDrinkToppings);
        order.setOrder(listOrderDrinks);

        double rt = Discount.getInstance().calcDiscount(order);
        Assertions.assertTrue(rt == 10.0);

    }


    private List<OrderDrink> generateOrderDrinks(int quantity, double price){
        List<OrderDrink> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            OrderDrink orderDrink = new OrderDrink();
            orderDrink.setDrinkUnitPrice(price);
            list.add(orderDrink);
        }
        return list;
    }

    private List<OrderDrinkTopping> generateOrderDrinkToppings(int quantity, double price){
        List<OrderDrinkTopping> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            OrderDrinkTopping orderDrinkTopping = new OrderDrinkTopping();
            orderDrinkTopping.setToppingUnitPrice(price);
            list.add(orderDrinkTopping);
        }
        return list;
    }
}
