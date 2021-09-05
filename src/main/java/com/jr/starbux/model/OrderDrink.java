package com.jr.starbux.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_drink")
@Entity
public class OrderDrink implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderDrinkId id;

	@JsonIgnore
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "drink_id")
	private Drink drink;

//	@JsonIgnore
	@Column(name = "drink_unit_price", nullable = false)
	private Double drinkUnitPrice;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;


}