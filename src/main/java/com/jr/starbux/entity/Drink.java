package com.jr.starbux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "drink")
@Entity
public class Drink extends BaseEntity implements Serializable {
	
	
	public Drink(Long id, String name, Double price) {
		super();
		super.setId(id);
		this.name = name;
		this.price = price;
	}

	
	private static final long serialVersionUID = 1L;


	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "price", nullable = false)
	private Double price;

}