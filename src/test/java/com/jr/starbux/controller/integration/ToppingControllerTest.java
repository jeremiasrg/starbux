package com.jr.starbux.controller.integration;


import com.jr.starbux.entity.Drink;
import com.jr.starbux.entity.Topping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToppingControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void shouldGetAllDrinksAndReturnStatusOK() throws Exception {
        ResponseEntity<ArrayList> response = template.getForEntity("/topping", ArrayList.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue(response.getBody().size() > 1);
    }

    @Test
    void shouldGetSpecificDrinksAndReturnStatusOK() throws Exception {
        ResponseEntity<Topping> response = template.getForEntity("/topping/3", Topping.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Chocolate sauce", response.getBody().getName());
    }

    @Test
    void shouldReturn404ErrorWithDrinkDontExist() throws Exception {
        ResponseEntity<Topping> response = template.getForEntity("/topping/300", Topping.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}
