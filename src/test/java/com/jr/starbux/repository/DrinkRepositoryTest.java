package com.jr.starbux.repository;

import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jr.starbux.entity.Drink;

@SpringBootTest
class DrinkRepositoryTest {

    @Autowired
    private DrinkRepository repository;


    @Test
     void shouldReturnMoreThanOneActiveDrink()  {
        List<Drink> rt = repository.findAllActived();
        Assertions.assertTrue(rt.size() > 1);
    }

    @Test
    void shouldReturnSpecificActiveDrink()  {
        Drink drink = repository.findByIdActived(2L);
        Assertions.assertNotNull(drink.getId());
    }


}
