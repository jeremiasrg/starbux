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
     void shouldReturnMoreThanOneDrink_WhenFindAllDrinks()  {
        List<Drink> rt = repository.findAllActived();
        Assertions.assertTrue(rt.size() > 1);
    }


}
