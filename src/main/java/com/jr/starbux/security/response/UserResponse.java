package com.jr.starbux.security.response;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.jr.starbux.security.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoleEnum role;

}
