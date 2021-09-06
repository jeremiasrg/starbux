package com.jr.starbux.math;

import com.jr.starbux.model.Order;
import com.jr.starbux.model.OrderDrink;

public class Discount {

	private static Discount instance;

	private Discount() {

	}

	public static Discount getInstance() {
		if (instance == null) {
			instance = new Discount();
		}
		return instance;
	}
	
	public  double calcDiscount(Order o) {
		Double disc1 = null;
		Double disc2 = null;
		
		if(o.getTotal() > 12) {
			 disc1 = o.getTotal()/100*25;
		}
		if(o.getOrder().size() >= 3) {
			disc2 = findLowestAmount(o);
		}
		
		if(disc1 != null && disc2 != null) {
			return disc1 < disc2 ? disc1 : disc2;
		}else if(disc1 != null ) {
			return disc1;
		}else if(disc2 != null ) {
			return disc2;
		}

		return 0.0;
	}
	
	private Double findLowestAmount(Order o) {
		Double minValue = null;
		for (OrderDrink od : o.getOrder() ) {
			if(minValue != null ) {
				double auxValue = od.getDrinkUnitPrice() + od.getTotalToppings();
				if(auxValue < minValue) {
					minValue = auxValue;
				}
			}else {
				minValue = od.getDrinkUnitPrice() + od.getTotalToppings();
			}
		}
		return minValue;
	}
}
