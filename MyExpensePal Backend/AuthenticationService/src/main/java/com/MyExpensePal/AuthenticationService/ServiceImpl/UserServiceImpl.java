package com.MyExpensePal.AuthenticationService.ServiceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.MyExpensePal.AuthenticationService.Dto.UpdateUserDto;
import com.MyExpensePal.AuthenticationService.Dto.UserDto;
import com.MyExpensePal.AuthenticationService.Dto.UserLoginDto;
import com.MyExpensePal.AuthenticationService.Entity.UserEntity;
import com.MyExpensePal.AuthenticationService.Exception.EMAIL_ALREADY_IN_USE_EXCEPTION;
import com.MyExpensePal.AuthenticationService.Exception.INCORRECT_PASSWORD_EXCEPTION;
import com.MyExpensePal.AuthenticationService.Exception.USER_NOT_FOUND_EXCEPTION;
import com.MyExpensePal.AuthenticationService.Mappers.UserMapper;
import com.MyExpensePal.AuthenticationService.Repository.UserRepository;
import com.MyExpensePal.AuthenticationService.Service.UserService;

import io.jsonwebtoken.Claims;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

	@Override
	public ResponseEntity<UserDto> registerUser(UserEntity user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Converting userEmail to lower case
		user.setEmail(user.getEmail().toLowerCase());
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

		if (userRepository.findById(userId).isEmpty())
			throw new USER_NOT_FOUND_EXCEPTION();
		userRepository.deleteById(userId);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> findUserById(UUID userId) throws USER_NOT_FOUND_EXCEPTION {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new USER_NOT_FOUND_EXCEPTION());
		return new ResponseEntity<UserDto>(UserMapper.EntityToDto(user), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Boolean> isUserExistsInDatabase(UUID userId) {
		if (userRepository.findById(userId).isEmpty())
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> updateUser(String userId, UpdateUserDto updateUserDto)
			throws USER_NOT_FOUND_EXCEPTION, EMAIL_ALREADY_IN_USE_EXCEPTION, INCORRECT_PASSWORD_EXCEPTION {
		UserEntity userEntity = userRepository.findById(UUID.fromString(userId)).get();

		if (userEntity == null) {

			throw new USER_NOT_FOUND_EXCEPTION();

		} else if (!updateUserDto.getEmail().equalsIgnoreCase(userEntity.getEmail())) {

			if (userRepository.existsByEmail(updateUserDto.getEmail()))
				throw new EMAIL_ALREADY_IN_USE_EXCEPTION();
			else
				// Implement Email Confirmation In Future.
				userEntity.setEmail(updateUserDto.getEmail().toLowerCase());

		} else if (updateUserDto.getOldPassword() != null && updateUserDto.getNewPassword() != null) {
			// Validating the old password
			if (passwordEncoder.matches(updateUserDto.getOldPassword(), userEntity.getPassword())) {
				userEntity.setPassword(passwordEncoder.encode(updateUserDto.getNewPassword()));
			} else
				throw new INCORRECT_PASSWORD_EXCEPTION();
		}

		userEntity.setFirstName(updateUserDto.getFirstName());
		userEntity.setLastName(updateUserDto.getLastName());
		userEntity.setGender(updateUserDto.getGender());
		userEntity.setPhone(updateUserDto.getPhone());
		userEntity.setBio(updateUserDto.getBio());
		userEntity.setDateOfBirth(updateUserDto.getDateOfBirth());

		UserEntity updatedUserEntity = userRepository.save(userEntity);

		return new ResponseEntity<UserDto>(UserMapper.EntityToDto(updatedUserEntity), HttpStatus.OK);
	}

}
