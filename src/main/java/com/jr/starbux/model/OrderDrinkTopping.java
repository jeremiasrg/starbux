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
@Builder
@Table(name = "order_drink_topping")
@Entity
public class OrderDrinkTopping implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
    private OrderDrinkToppingId id;

}