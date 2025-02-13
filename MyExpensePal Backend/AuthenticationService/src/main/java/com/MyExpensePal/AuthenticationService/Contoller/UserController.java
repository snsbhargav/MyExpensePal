package com.MyExpensePal.AuthenticationService.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MyExpensePal.AuthenticationService.Dto.UserDto;
import com.MyExpensePal.AuthenticationService.Entity.UserEntity;
import com.MyExpensePal.AuthenticationService.Service.UserService;

@RestController("/api/auth/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public UserDto registerUser(@RequestBody UserEntity user) {
		return userService.registerUser(user);
	}

}
