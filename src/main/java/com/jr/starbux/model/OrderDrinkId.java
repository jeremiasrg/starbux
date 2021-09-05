package com.jr.starbux.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class OrderDrinkId implements Serializable {
	private static final long serialVersionUID = 4041665632631291301L;
	

	@Column(name = "order_id", nullable = false)
	private Long orderId;
	

	@Column(name = "drink_id", nullable = false)
	private Long drinkId;

	
}