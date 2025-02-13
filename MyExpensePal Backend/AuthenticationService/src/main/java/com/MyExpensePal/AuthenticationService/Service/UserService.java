package com.MyExpensePal.AuthenticationService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MyExpensePal.AuthenticationService.Dto.UserDto;
import com.MyExpensePal.AuthenticationService.Entity.UserEntity;
import com.MyExpensePal.AuthenticationService.Mappers.UserMapper;
import com.MyExpensePal.AuthenticationService.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserDto registerUser(UserEntity user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return UserMapper.EntityToDto(userRepository.save(user));
	}
	
}
