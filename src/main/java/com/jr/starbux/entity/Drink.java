package com.jr.starbux.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "drink")
@Entity
public class Drink extends BaseModel implements Serializable {
	
	
	public Drink(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "price", nullable = false)
	private Double price;
	

//	@JsonIgnore
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "drink", cascade = CascadeType.ALL)
//	private List<OrderDrink> orders = new ArrayList<>();
	
	@Column(name = "active", columnDefinition = "bit default 0", nullable = true)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active;
	
	@PrePersist
	void preInsert() {
		if (this.active == null)
			this.active = true;
	}

}