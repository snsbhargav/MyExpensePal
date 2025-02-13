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

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public ResponseEntity<UserDto> registerUser(UserEntity user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(UserMapper.EntityToDto(userRepository.save(user)), HttpStatus.CREATED);
	}

	public ResponseEntity<String> validateUser(UserLoginDto loginDto) {
		//TODO check password then generate token.
		return new ResponseEntity<String>(jwtService.generateToken(loginDto.getEmail()), HttpStatus.OK);
	}

}
