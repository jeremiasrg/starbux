package com.jr.starbux.controller.integration;


import com.jr.starbux.entity.Drink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DrinkControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void shouldGetAllDrinksAndReturnStatusOK() throws Exception {
        ResponseEntity<ArrayList> response = template.getForEntity("/drink", ArrayList.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue(response.getBody().size() > 1);
    }

    @Test
    void shouldGetSpecificDrinksAndReturnStatusOK() throws Exception {
        ResponseEntity<Drink> response = template.getForEntity("/drink/3", Drink.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Mocha", response.getBody().getName());
    }

    @Test
    void shouldReturn404ErrorWithDrinkDontExist() throws Exception {
        ResponseEntity<Drink> response = template.getForEntity("/drink/300", Drink.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}
