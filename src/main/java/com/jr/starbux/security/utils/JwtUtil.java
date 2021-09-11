package com.jr.starbux.security.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jr.starbux.security.request.UserJwtRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	private Date expirationDate() {
		return new Date(System.currentTimeMillis() * expiration * 60000);
	}

	public String generateToken(UserJwtRequest userJwtRequest) {
		return Jwts.builder().setSubject(userJwtRequest.getEmail()).setExpiration(expirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public String getUser(String token) throws ExpiredJwtException {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

	public Date getDateExpiration(String token) {
		Claims claims = getClaims(token);
		return claims.getExpiration();
	}

	public boolean isTokenExpired(String token) {
		final Date expirationDate = getDateExpiration(token);
		return expirationDate.before(new Date());
	}

	public boolean isTokenValid(String token) {
		return !isTokenExpired(token);
	}

}
