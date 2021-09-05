package com.jr.starbux.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_drink_topping", indexes = {
        @Index(name = "fk_OrderDrink_has_Topping_Topping1_idx", columnList = "topping_id"),
        @Index(name = "fk_OrderDrink_has_Topping_OrderDrink1_idx", columnList = "order_drink_order_id, order_drink_drink_id")
})
@Entity
public class OrderDrinkTopping implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
    private OrderDrinkToppingId id;

}