package com.jr.starbux.controller.integration;

import com.jr.starbux.StarbuxApplication;
import com.jr.starbux.request.DrinkRequest;
import com.jr.starbux.request.ToppingRequest;
import com.jr.starbux.response.DrinkResponse;
import com.jr.starbux.response.ToppingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {StarbuxApplication.class, com.jr.starbux.SecurityConfiguration.class})
 class AdminControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void shouldPostNewDrink() {
        DrinkRequest drink = new DrinkRequest();
        drink.setName("test Drink");
        drink.setPrice(1.0);
        HttpEntity<ToppingRequest> request = new HttpEntity(drink);

        ResponseEntity<DrinkResponse> response = template.postForEntity("/admin/drink", request, DrinkResponse.class);

        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals( HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldPostNewTopping() {
        ToppingRequest topping = new ToppingRequest();
        topping.setName("test Topping");
        topping.setPrice(1.0);
        HttpEntity<ToppingRequest> request = new HttpEntity(topping);
        ResponseEntity<ToppingResponse> response = template.postForEntity("/admin/drink", request, ToppingResponse.class);

        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
