package com.jr.starbux.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class OrderDrink extends CommunField  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@JsonIgnore
	@Column(name = "order_id", nullable = false)
	private Long orderId;
	
	@JsonIgnore
	@Column(name = "drink_id", nullable = false)
	private Long drinkId;

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
	List<OrderDrinkTopping> toppings = new ArrayList<OrderDrinkTopping>();

	@JsonIgnore
	@Column(name = "drink_unit_price", nullable = false)
	private Double drinkUnitPrice;
	
	
	@Transient
	@JsonIgnore
    @JsonGetter(value = "totalToppings")
	public Double getTotalToppings() {
		return this.getToppings()
				.stream()
				.map(v -> v.getToppingUnitPrice())
				.reduce(0.0, (a, b) -> a + b);
	}


}