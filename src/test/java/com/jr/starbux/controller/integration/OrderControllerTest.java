package com.jr.starbux.controller.integration;

import com.jr.starbux.entity.*;
import com.jr.starbux.request.*;
import com.jr.starbux.response.OrderResponse;
import com.jr.starbux.security.entity.User;
import com.jr.starbux.security.request.UserRequest;
import com.jr.starbux.security.response.UserJwtResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void shouldGetAllOrdersAndReturnStatusOK() throws Exception {
        ResponseEntity<ArrayList> response = template.getForEntity("/order", ArrayList.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturn404ErrorWithOrderDontExist() throws Exception {
        ResponseEntity<Order> response = template.getForEntity("/topping/300", Order.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldPostCreateNewOrder() throws Exception {

        ClientRequest cli = new ClientRequest();
        cli.setId(1L);

        // Order
        OrderRequest order = new OrderRequest();
        order.setClient(cli);
        order.setAddress("My test address");

        // Order Drink
        OrderDrinkRequest orderDrink = new OrderDrinkRequest();
        orderDrink.setDrink(new DrinkRequest(2L, null, null));

        // Order Drink Topping
        OrderDrinkToppingRequest odt = new OrderDrinkToppingRequest();

        // Drink 1
        odt.setTopping(new ToppingRequest(2L, null, null));
        List<OrderDrinkRequest> listOrderDrink = new ArrayList<>();
        List<OrderDrinkToppingRequest> listOrderDrinkTopping = new ArrayList<>();
        listOrderDrinkTopping.add(odt);
        listOrderDrink.add(orderDrink);
        listOrderDrink.get(0).setToppings(listOrderDrinkTopping);

        // Drink 2
        odt.setTopping(new ToppingRequest(3L, null, null));
        listOrderDrinkTopping.add(odt);
        listOrderDrink.add(orderDrink);
        listOrderDrink.get(1).setToppings(listOrderDrinkTopping);

        // Drink 3
        odt.setTopping(new ToppingRequest(4L, null, null));
        listOrderDrinkTopping.add(odt);
        listOrderDrink.add(orderDrink);
        listOrderDrink.get(2).setToppings(listOrderDrinkTopping);

        // Finalize object
        order.setOrder(listOrderDrink);


        HttpEntity<User> request = new HttpEntity(order);
        ResponseEntity<OrderResponse> response = template.postForEntity("/order",request, OrderResponse.class);
        Assertions.assertNotNull(response.getBody().getId());
    }
}
