package com.jr.starbux.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDrinkResponse {

	private Long id;
	private DrinkResponse drink;
	private List<OrderDrinkToppingResponse> toppings = new ArrayList<>();
}
