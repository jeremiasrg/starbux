package com.jr.starbux.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude="toppings")
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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderDrink", cascade = CascadeType.ALL)
	Set<OrderDrinkTopping> toppings = new HashSet<OrderDrinkTopping>();

	@JsonIgnore
	@Column(name = "drink_unit_price", nullable = false)
	private Double drinkUnitPrice;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Transient
	@JsonIgnore
    @JsonGetter(value = "totalToppings")
	public Double getTotalToppings() {
		return this.getToppings()
				.stream()
				.map(v -> v.getToppingUnitPrice() * v.getQuantity())
				.reduce(0.0, (a, b) -> a + b);
	}


}