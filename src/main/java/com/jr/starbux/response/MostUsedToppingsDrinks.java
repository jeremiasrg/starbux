package com.jr.starbux.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MostUsedToppingsDrinks {

	
	private String topping;
	private Long total;

}
