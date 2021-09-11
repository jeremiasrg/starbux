package com.jr.starbux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name = "topping")
@Entity
public class Topping extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Topping(Long id, String name, Double price) {
		super.setId(id);
		this.name = name;
		this.price = price;
	}

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "active", columnDefinition = "bit default 0", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active;

}