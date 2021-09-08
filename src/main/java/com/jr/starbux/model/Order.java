package com.jr.starbux.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude="order")
@Table(name = "sb_order")
@Entity
public class Order extends BaseModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "date_time", nullable = false)
	private Instant dateTime;

	@Column(name = "customer_name", nullable = false, length = 100)
	private String customerName;
	
	@Column(name = "discount", nullable = false, length = 100)
	private double discount;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDrink> order = new ArrayList<OrderDrink>();

	@Column(name = "active", columnDefinition = "bit default 0", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active;
	

	@PrePersist
	 void preInsert() {
		System.out.println(this.active);
		if (this.dateTime == null)
			this.dateTime = Instant.now();
		if (this.active == null)
			this.active = true;
		
		System.out.println(this.active);
	
	}

	@Transient
    @JsonGetter(value = "total")
	public Double getTotal() {
		Double v1 = this.getOrder()
				.stream()
				.filter( v-> v.getTotalToppings() != null)
				.map(v -> v.getTotalToppings()).reduce(0.0, (a, b) -> a + b);
		Double v2 = this.getOrder()
				.stream()
				.filter( v-> v.getDrinkUnitPrice() != null)
				.map(v -> v.getDrinkUnitPrice())
				.reduce(0.0, (a, b) -> a + b);
		return (v1+v2);
	}
}