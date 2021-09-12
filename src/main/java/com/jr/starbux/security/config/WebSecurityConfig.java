package com.jr.starbux.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jr.starbux.security.jwt.JwtAuthenticationTokenFilter;
import com.jr.starbux.security.service.MyUserDetailsService;
import com.jr.starbux.security.utils.JwtUtil;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	
	@Bean
	public OncePerRequestFilter jwtAuthenticationTokenFilter(){
		return new JwtAuthenticationTokenFilter(this.userDetailsService, this.jwtUtil);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
//				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/user/**").permitAll()
				.antMatchers("/drink/**").permitAll()
				.antMatchers("/topping/**").permitAll()
				.antMatchers("/order/**").permitAll()
				.antMatchers("/client/**").permitAll()
				.antMatchers("/admin/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
}
