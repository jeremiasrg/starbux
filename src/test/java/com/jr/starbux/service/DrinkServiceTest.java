package com.jr.starbux.service;

import java.util.List;

import com.jr.starbux.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jr.starbux.entity.Drink;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DrinkServiceTest {

    @Autowired
    private DrinkService service;

    @Test
    @Order(1)
    void shouldUpdateSpecificDrink() throws Exception {
        Drink drink = service.find(1L);
        String originalName = drink.getName();
        drink.setName(originalName + "_test");
        service.update(1L, drink);
        Drink test = service.find(1L);
        Assertions.assertEquals(test.getName(), "Black Coffee" + "_test");
    }

    @Test
    @Order(2)
    void shouldReturnMoreThanOneDrink() {
        List<Drink> drinks = service.findAll();
        Assertions.assertTrue(drinks.size() > 1);
    }

    @Test
    @Order(3)
    void shouldReturnSpecificDrink() throws Exception {
        Drink rt = service.find(2L);
        Assertions.assertEquals("Latte", rt.getName());
    }

    @Test
    @Order(4)
    void shouldCreateDrink() throws Exception {
        Drink drink = new Drink();

        drink.setName("Drink unit test");
        drink.setPrice(100.00);
        Drink rt = service.save(drink);
        Assertions.assertNotNull(rt.getId());
    }

    @Test
    @Order(5)
    void shouldDeleteDrink() throws Exception {
        service.delete(1L);
        Assertions.assertThrows(ObjectNotFoundException.class, () -> service.find(1L));
    }
}
