package com.Project.MyExpensePal.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Project.MyExpensePal.Entity.UserEntity;
import com.Project.MyExpensePal.Model.UserModel;

@Service
public interface UserService {

	public String saveUser(UserEntity userEntity);

	public ResponseEntity<UserModel> retrieveUserById(Long userId);

	public ResponseEntity<Boolean> deleteUserFromDatabase(Long userId);
}
