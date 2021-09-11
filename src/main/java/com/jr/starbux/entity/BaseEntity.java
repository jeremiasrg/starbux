package com.jr.starbux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "active", columnDefinition = "bit default 1")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active;
	
	@PrePersist
	void preInsert() {
		if (this.active == null)
			this.setActive(true);
	}

}
