package com.MyExpensePal.AuthenticationService.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String secretKey = "";

	public JwtService() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
			secretKey = Base64.getEncoder()
					.encodeToString(keyGenerator.generateKey().getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String generateToken(String email) {
		// TODO generate token.
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().claims(claims).subject(email).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 30 * 60 * 60)).signWith(getKey()).compact();
	}

	private Key getKey() {
		
		return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
	}

}
