package com.jr.starbux.request;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDrinkRequest {

	private Long id;
    private DrinkRequest drink;
    private List<OrderDrinkToppingRequest> toppings = new ArrayList<>();
}
