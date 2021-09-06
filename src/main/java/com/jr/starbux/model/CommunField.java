package com.jr.starbux.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
public abstract class CommunField  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "active", columnDefinition = "bit default 0", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	protected Boolean active;
	
	@PrePersist
	void preInsert() {
		if (this.active == null)
			this.active = true;
	}

}
