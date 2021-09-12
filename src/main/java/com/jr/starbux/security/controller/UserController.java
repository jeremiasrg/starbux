package com.jr.starbux.security.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jr.starbux.security.entity.User;
import com.jr.starbux.security.request.UserJwtRequest;
import com.jr.starbux.security.request.UserRequest;
import com.jr.starbux.security.response.UserJwtResponse;
import com.jr.starbux.security.response.UserResponse;
import com.jr.starbux.security.service.MyUserDetailsService;
import com.jr.starbux.security.service.UserService;
import com.jr.starbux.security.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private final MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public UserResponse save(@RequestBody @Valid UserRequest userRequest) {
		log.info("Method findAll called");
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		userService.save(user);

		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		return userResponse;
	}
	
	@PostMapping("/authenticate")
	@ResponseStatus(HttpStatus.OK)
	public UserJwtResponse getAutentication(@RequestBody UserJwtRequest userJwtRequest ) {
		try {
			UserDetails userDetails = userDetailsService.autenticate(userJwtRequest);
			String token = jwtUtil.generateToken(userJwtRequest);
			return UserJwtResponse
					.builder()
					.email(userDetails.getUsername())
					.token(token)
					.build();
			
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
}
