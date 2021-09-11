package com.jr.starbux.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="toppings")
@Table(name = "order_drink")
@Entity
public class OrderDrink extends BaseModel  implements Serializable {
	
	
	public OrderDrink(Long id, Long orderId, Long drinkId, List<OrderDrinkTopping> toppings) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.drinkId = drinkId;
		this.toppings = toppings;
	}


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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderDrink", cascade = CascadeType.MERGE)
	private List<OrderDrinkTopping> toppings = new ArrayList<>();

	@JsonIgnore
	@Column(name = "drink_unit_price", nullable = false)
	private Double drinkUnitPrice;
	
	@Column(name = "active", columnDefinition = "bit default 0", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active;
	
	@PrePersist
	void preInsert() {
		if (this.active == null)
			this.active = true;
	}
	
	
	@Transient
	@JsonIgnore
    @JsonGetter(value = "totalToppings")
	public Double getTotalToppings() {
		return this.getToppings()
				.stream()
				.map(v -> v.getToppingUnitPrice())
				.filter(Objects::nonNull)
				.reduce(0.0, (a, b) -> a + b);
	}


}