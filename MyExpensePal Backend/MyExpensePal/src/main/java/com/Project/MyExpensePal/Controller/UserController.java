package com.Project.MyExpensePal.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project.MyExpensePal.Entity.UserEntity;
import com.Project.MyExpensePal.Service.UserService;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("/saveUser")
	public ResponseEntity<String> addNewUser(@RequestBody UserEntity user) {
		return new ResponseEntity<String>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/retrieveUser/{id}")
	public ResponseEntity<UserEntity> retrieveUser(@PathVariable("id") Long userId) {
		return userService.retrieveUserById(userId);
	}

	@GetMapping("/tester1")
	public String test() {
		return "test successful";
	}

	@DeleteMapping("removeUser/{userId}")
	public ResponseEntity<Boolean> removeUser(@PathVariable("userId") Long userId) {
		return userService.deleteUserFromDatabase(userId);
	}

}
