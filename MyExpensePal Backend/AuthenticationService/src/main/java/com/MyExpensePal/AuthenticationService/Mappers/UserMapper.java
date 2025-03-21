package com.MyExpensePal.AuthenticationService.Mappers;

import com.MyExpensePal.AuthenticationService.Dto.UserDto;
import com.MyExpensePal.AuthenticationService.Entity.UserEntity;

public class UserMapper {
	
	public static UserDto EntityToDto(UserEntity userEntity) {
		return UserDto.builder()
				.userId(userEntity.getUserId())
				.bio(userEntity.getBio())
				.dateOfBirth(userEntity.getDateOfBirth())
				.email(userEntity.getEmail())
				.firstName(userEntity.getFirstName())
				.gender(userEntity.getGender())
				.lastName(userEntity.getLastName())
				.phone(userEntity.getPhone())
				.build();
	}
	
}
