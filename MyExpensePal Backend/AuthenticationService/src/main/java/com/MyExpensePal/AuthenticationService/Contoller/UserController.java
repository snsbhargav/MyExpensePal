package com.MyExpensePal.AuthenticationService.Contoller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyExpensePal.AuthenticationService.Dto.UserDto;
import com.MyExpensePal.AuthenticationService.Dto.UserLoginDto;
import com.MyExpensePal.AuthenticationService.Entity.UserEntity;
import com.MyExpensePal.AuthenticationService.Exception.USER_NOT_FOUND_EXCEPTION;
import com.MyExpensePal.AuthenticationService.Service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserEntity user) {
		return userService.registerUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserLoginDto loginDto) {
		return userService.validateUser(loginDto);
	}

	@PostMapping("/validateToken")
	public ResponseEntity<Boolean> validateToken(
			@RequestHeader(value = "Authorization", required = false) String authHeader) throws USER_NOT_FOUND_EXCEPTION {
		if (authHeader == null || !authHeader.startsWith("Bearer "))
			return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);

		String token = authHeader.substring(7);
		return userService.validateToken(token);
	}
	
	@DeleteMapping("/removeUser/{userId}")
	public ResponseEntity<Boolean> removeUser(@PathVariable("userId") UUID userId) throws USER_NOT_FOUND_EXCEPTION {
		return userService.deleteUserFromDatabase(userId);
	}
	
	@GetMapping("/getUserByEmail/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) throws USER_NOT_FOUND_EXCEPTION{
		return userService.findUserByEmail(email);
	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<UserDto> getUSerById(@PathVariable UUID userId) throws USER_NOT_FOUND_EXCEPTION{
		return userService.finUserById(userId);
	}
	
	//For testing
	@GetMapping("test")
	public String test() {
		return "test";
	}

}
