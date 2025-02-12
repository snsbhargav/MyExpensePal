package com.Project.MyExpensePal.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project.MyExpensePal.Entity.UserEntity;
import com.Project.MyExpensePal.Model.UserModel;
import com.Project.MyExpensePal.Service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/saveUser")
	public ResponseEntity<String> addNewUser(@RequestBody UserEntity user){
		return new ResponseEntity<String>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/retrieveUser/{id}")
	public ResponseEntity<UserEntity> retrieveUser(@PathVariable("id") Long userId){
		return userService.retrieveUserById(userId);
	}
	
	@GetMapping("/tester1")
	public String test() {
		return "test successful";
	}
	@DeleteMapping("removeUser/{userId}")
	public ResponseEntity<Boolean> removeUser(@PathVariable("userId") Long userId){
		return userService.deleteUserFromDatabase(userId);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserEntity user){
		if(!userService.isUserAlreadyExists(user.getEmail()).getBody()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return new ResponseEntity<String>(userService.saveUser(user), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User already exists", HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	@PostMapping("/login1")
	public ResponseEntity<?> login(HttpSession session, @RequestBody UserEntity userEntity){
		UserEntity existingUser = userService.retrieveUserById(userEntity.getUserId()).getBody();
		if(existingUser != null && passwordEncoder.matches(userEntity.getPassword(), existingUser.getPassword())) {
			session.setAttribute("user", userEntity);
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
	}
	
	@GetMapping("/logout1")
	public ResponseEntity<?> logout(HttpSession session){
		session.invalidate();
		return ResponseEntity.ok("Logged Out Successfully.");
	}
	
	
	@GetMapping("/session-user")
	public ResponseEntity<?> getSessionUser(HttpSession session){
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		if(userEntity == null) 
			return new ResponseEntity<>("No User Found in Session", HttpStatus.UNAUTHORIZED);
		else
			return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}
}
