package com.jr.starbux.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jr.starbux.response.MostUsedToppingsDrinks;
import com.jr.starbux.response.TotalAmountCustomer;

@SpringBootTest
 class AdminServiceTest {

	@Autowired
	private AdminService service;

	@Test
	void shouldReturnReportMostUsedToppingsDrinks() {
		List<MostUsedToppingsDrinks> rt = service.mostUsedToppingsDrinks();
		Assertions.assertNotNull(rt);
	}

	@Test
	void shouldReturnTotalAmountCustomer() {
		List<TotalAmountCustomer> rt = service.totalAmountCustomer();
		Assertions.assertNotNull(rt);
	}
}
