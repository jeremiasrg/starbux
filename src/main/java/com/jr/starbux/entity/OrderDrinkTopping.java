package com.jr.starbux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_drink_topping")
@NoArgsConstructor
@Entity
public class OrderDrinkTopping  extends BaseModel  implements Serializable {
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

	@JsonIgnore
	@Column(name = "topping_unit_price", nullable = false)
	private Double toppingUnitPrice;
	
	@Column(name = "active", columnDefinition = "bit default 0", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	protected Boolean active;
	
	@PrePersist
	void preInsert() {
		if (this.active == null)
			this.active = true;
	}


}