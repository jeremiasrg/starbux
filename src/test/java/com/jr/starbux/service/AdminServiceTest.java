package com.jr.starbux.service;

import com.jr.starbux.response.MostUsedToppingsDrinks;
import com.jr.starbux.response.TotalAmountCustomer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService service;

    @Test
    public void shouldReturnReportMostUsedToppingsDrinks_WhenCallMostUsedToppingsDrinks(){
        List<MostUsedToppingsDrinks> rt = service.mostUsedToppingsDrinks();
        Assertions.assertNotNull(rt);
    }

    @Test
    public void shouldReturnTotalAmountCustomer_WhenTotalAmountCustomer(){
        List<TotalAmountCustomer> rt = service.totalAmountCustomer("test");
        Assertions.assertNotNull(rt);
    }
}
