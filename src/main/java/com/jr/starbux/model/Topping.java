package com.jr.starbux.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(exclude="toppings")
@Table(name = "topping")
@Entity
public class Topping implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "active", columnDefinition = "bit default 0", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "topping", cascade = CascadeType.ALL)
//	Set<OrderDrinkTopping> toppings = new HashSet<OrderDrinkTopping>();
	
	@PrePersist
	void preInsert() {
		if (this.active == null)
			this.active = true;
	}

}