package com.jr.starbux.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseModel  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@JsonIgnore
//	@Column(name = "active", columnDefinition = "bit default 0", nullable = false)
//	@Type(type = "org.hibernate.type.NumericBooleanType")
//	private Boolean active;
//
//	public Boolean getActive() {
//		if(this.active == null) return true;
//		
//		return active;
//	}
//
//	public void setActive(Boolean active) {
//		this.active = active;
//	}

}
