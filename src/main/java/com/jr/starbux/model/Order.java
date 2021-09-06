package com.jr.starbux.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonGetter;

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
public class Order implements Serializable {
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
	Set<OrderDrink> order = new HashSet<OrderDrink>();


	@PrePersist
	private void prePersist() {
		if (this.dateTime == null)
			this.dateTime = Instant.now();
	}

	@Transient
    @JsonGetter(value = "total")
	public Double getTotal() {
		Currency c  = Currency.getInstance("EUR");
		Double v1 = this.getOrder().stream().map(v -> v.getTotalToppings()).reduce(0.0, (a, b) -> a + b);
		Double v2 = this.getOrder()
				.stream()
				.map(v -> v.getDrinkUnitPrice() * v.getQuantity())
				.reduce(0.0, (a, b) -> a + b);
		return (v1+v2);
	}
}