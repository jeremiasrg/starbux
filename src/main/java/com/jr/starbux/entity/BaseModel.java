package com.jr.starbux.entity;

import java.io.Serializable;


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
