package com.jr.starbux.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDrinkToppingRequest {
	
	private Long id;
    private OrderDrinkRequest orderDrink;
    private ToppingRequest topping;
}
