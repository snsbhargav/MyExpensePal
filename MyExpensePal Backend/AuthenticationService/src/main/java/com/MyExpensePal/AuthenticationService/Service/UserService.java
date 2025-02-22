package com.MyExpensePal.AuthenticationService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MyExpensePal.AuthenticationService.Dto.UserDto;
import com.MyExpensePal.AuthenticationService.Dto.UserLoginDto;
import com.MyExpensePal.AuthenticationService.Entity.UserEntity;
import com.MyExpensePal.AuthenticationService.Mappers.UserMapper;
import com.MyExpensePal.AuthenticationService.Repository.UserRepository;

import io.jsonwebtoken.Claims;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

	public ResponseEntity<UserDto> registerUser(UserEntity user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(UserMapper.EntityToDto(userRepository.save(user)), HttpStatus.CREATED);
	}

	public ResponseEntity<String> validateUser(UserLoginDto loginDto) {
		UserEntity user = userRepository.findByEmail(loginDto.getEmail());

		if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			return new ResponseEntity<String>(jwtService.generateToken(loginDto.getEmail()), HttpStatus.OK);
		}
		return new ResponseEntity<String>("No User Found", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Boolean> validateToken(String token) {
		Claims claims;
		try {
			claims = jwtService.getClaims(token);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		String email = jwtService.getSubject(claims);
		UserDto user = findUserByEmail(email);
		
		if (user != null && jwtService.isTokenValid(claims))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}

	public UserDto findUserByEmail(String email) {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null)
			return null;
		return UserMapper.EntityToDto(user);
	}

}
