package com.jr.starbux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_drink_topping")
@NoArgsConstructor
@Entity
public class OrderDrinkTopping extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "order_id", nullable = false)
	private Long orderId;

	@Column(name = "drink_id", nullable = false)
	private Long drinkId;

	@Column(name = "topping_id", nullable = false)
	private Long toppingId;

	@Column(name = "order_drink_id", nullable = false)
	private Long orderDrinkId;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "topping_id")
	private Topping topping;

	@JsonIgnore
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "order_drink_id", nullable = false)
	private OrderDrink orderDrink;

	@Column(name = "topping_unit_price", nullable = false)
	private Double toppingUnitPrice;

	@Column(name = "active", columnDefinition = "bit default 0", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	protected Boolean active;

}