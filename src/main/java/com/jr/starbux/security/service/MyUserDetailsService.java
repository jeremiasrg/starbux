package com.jr.starbux.security.service;

import java.util.ArrayList;
import java.util.List;

import com.jr.starbux.security.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jr.starbux.security.enums.RoleEnum;
import com.jr.starbux.security.repository.UserRepository;
import com.jr.starbux.security.request.UserJwtRequest;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.jr.starbux.security.entity.User user = repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

		return User.builder().username(user.getEmail()).password(user.getPassword())
				.authorities(mapToGrantedAuthorities(user.getRole())).build();
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(RoleEnum roleEnum) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
		return authorities;
	}

	public UserDetails autenticate(UserJwtRequest userJwtRequest) throws InvalidPasswordException {
		UserDetails userDetails = loadUserByUsername(userJwtRequest.getEmail());
		boolean isValidPassword = passwordEncoder.matches(userJwtRequest.getPassword(), userDetails.getPassword());
		
		if(isValidPassword) return userDetails;
		throw new InvalidPasswordException();
	}

}
