package com.jr.starbux.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Table(name = "order_drink_topping")
@NoArgsConstructor
@Entity
public class OrderDrinkTopping implements Serializable{
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
	@Column(name = "topping_id", nullable = false)
    private Long toppingId;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "topping_id")
	private Topping topping;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("id")
	@JoinColumns( {
	    @JoinColumn(name="drink_id", nullable = false),
	    @JoinColumn(name="order_id", nullable = false)} )
	private OrderDrink orderDrink;
	
	@JsonIgnore
	@Column(name = "topping_unit_price", nullable = false)
	private Double toppingUnitPrice;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
}