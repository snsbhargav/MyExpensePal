package com.MyExpensePal.AuthenticationService.ServiceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.MyExpensePal.AuthenticationService.Dto.UserDto;
import com.MyExpensePal.AuthenticationService.Dto.UserLoginDto;
import com.MyExpensePal.AuthenticationService.Entity.UserEntity;
import com.MyExpensePal.AuthenticationService.Exception.USER_NOT_FOUND_EXCEPTION;
import com.MyExpensePal.AuthenticationService.Mappers.UserMapper;
import com.MyExpensePal.AuthenticationService.Repository.UserRepository;
import com.MyExpensePal.AuthenticationService.Service.UserService;

import io.jsonwebtoken.Claims;

@Component
public class UserServiceImpl implements UserService{

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

	public ResponseEntity<Boolean> validateToken(String token) throws USER_NOT_FOUND_EXCEPTION {
		Claims claims;
		try {
			claims = jwtService.getClaims(token);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		String email = jwtService.getSubject(claims);
		UserDto user = findUserByEmail(email).getBody();
		
		if (user != null && jwtService.isTokenValid(claims))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<UserDto> findUserByEmail(String email) throws USER_NOT_FOUND_EXCEPTION {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null)
			throw new USER_NOT_FOUND_EXCEPTION();
		return new ResponseEntity<UserDto>(UserMapper.EntityToDto(user), HttpStatus.CREATED);
	}

	public ResponseEntity<Boolean> deleteUserFromDatabase(UUID userId) throws USER_NOT_FOUND_EXCEPTION {
		
		if(userRepository.findById(userId).isEmpty())
			throw new USER_NOT_FOUND_EXCEPTION();
		userRepository.deleteById(userId);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
