package com.MyExpensePal.AuthenticationService.Service;


import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secretKey}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().claims(claims).subject(email).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getKey()).compact();
	}

	private SecretKey getKey() {

		return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
	}
	
	public Claims getClaims(String token) {
		final Claims claims = Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
		return claims;
	}

	public String getSubject(Claims claims) {
		return claims.getSubject();
	}

	public boolean isTokenValid(Claims claims) {
		return isExpired(claims);
	}
	
	private boolean isExpired(Claims claims) {
		Date expiry = claims.getExpiration();
		return new Date().before(expiry);
	}

}
