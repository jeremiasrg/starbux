package com.jr.starbux.security.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import com.jr.starbux.security.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

	@NotEmpty(message = "Email is required.")
	private String email;
	
	@NotEmpty(message = "Password is required.")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
}
