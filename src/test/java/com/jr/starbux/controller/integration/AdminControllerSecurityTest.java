package com.jr.starbux.controller.integration;

import com.jr.starbux.request.DrinkRequest;
import com.jr.starbux.request.ToppingRequest;
import com.jr.starbux.response.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerSecurityTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void shouldReturnForbiddenWhenGetTotalAmountCustomerWithNoToken() {
        ResponseEntity<TotalAmountCustomer> response = template.getForEntity("/admin/totalAmountCustomer", TotalAmountCustomer.class);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void shouldReturnForbiddenWhenGetMostUsedToppingsDrinksWithNoToken() {
        ResponseEntity<MostUsedToppingsDrinks> response = template.getForEntity("/admin/mostUsedToppingsDrinks", MostUsedToppingsDrinks.class);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

//    @Test
//    void shouldReturnForbiddenWhenDeleteDrinkWithNoToken() {
//        ResponseEntity<Void> response = template.exchange("/admin/drink", HttpMethod.DELETE, new HttpEntity<>(null), Void.class, 1L);
//        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
//    }
//
//    @Test
//    void shouldReturnForbiddenWhenPutUpdateDrinkWithNoToken() {
//        ResponseEntity<Void> response = template.exchange("/admin/drink/1", HttpMethod.PUT, new HttpEntity<>(null), Void.class, 1L);
//        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
//
//    }

    @Test
    void shouldReturnForbiddenWhenCreateToppingWithNoToken() {
        ToppingRequest topping = new ToppingRequest();
        topping.setName("test Topping");
        topping.setPrice(1.0);
        HttpEntity<ToppingRequest> request = new HttpEntity(topping);
        ResponseEntity<ToppingResponse> response = template.postForEntity("/admin/drink", request, ToppingResponse.class);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void shouldReturnForbiddenWhenCreateDrinkWithNoToken() {
        DrinkRequest drink = new DrinkRequest();
        drink.setName("test Drink");
        drink.setPrice(1.0);
        HttpEntity<DrinkRequest> request = new HttpEntity(drink);
        ResponseEntity<DrinkResponse> response = template.postForEntity("/admin/topping", request, DrinkResponse.class);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
