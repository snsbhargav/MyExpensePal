package com.MyExpensePal.AuthenticationService.ServiceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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

	@Override
	public ResponseEntity<UserDto> registerUser(UserEntity user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(UserMapper.EntityToDto(userRepository.save(user)), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> validateUser(UserLoginDto loginDto) {
		UserEntity user = userRepository.findByEmail(loginDto.getEmail());

		if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			return new ResponseEntity<String>(jwtService.generateToken(user.getUserId()), HttpStatus.OK);
		}
		return new ResponseEntity<String>("No User Found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<UUID> validateToken(String token) throws USER_NOT_FOUND_EXCEPTION {
		Claims claims;
		try {
			claims = jwtService.getClaims(token);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		UUID userId = jwtService.getSubject(claims);
		UserDto user = findUserById(userId).getBody();
		
		if (user != null && jwtService.isTokenValid(claims))
			return new ResponseEntity<UUID>(userId, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@Override
	public ResponseEntity<UserDto> findUserByEmail(String email) throws USER_NOT_FOUND_EXCEPTION {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null)
			throw new USER_NOT_FOUND_EXCEPTION();
		return new ResponseEntity<UserDto>(UserMapper.EntityToDto(user), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Boolean> deleteUserFromDatabase(UUID userId) throws USER_NOT_FOUND_EXCEPTION {
		
		if(userRepository.findById(userId).isEmpty())
			throw new USER_NOT_FOUND_EXCEPTION();
		userRepository.deleteById(userId);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> findUserById(UUID userId) throws USER_NOT_FOUND_EXCEPTION {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new USER_NOT_FOUND_EXCEPTION());
		return new ResponseEntity<UserDto>(UserMapper.EntityToDto(user), HttpStatus.CREATED);	}

	@Override
	public ResponseEntity<Boolean> isUserExistsInDatabase(UUID userId) {
		if(userRepository.findById(userId).isEmpty())
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
