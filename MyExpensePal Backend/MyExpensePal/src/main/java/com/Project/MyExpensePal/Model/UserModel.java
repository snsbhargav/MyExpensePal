package com.Project.MyExpensePal.Model;

import java.sql.Date;

import com.Project.MyExpensePal.Entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserModel {

	
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private Date dateOfBirth;
	private String bio;
	private String phone;
	private int income;
	
	public static UserModel convertEntityToModel(UserEntity userEntity) {
		
		return UserModel.builder()
				.userId(userEntity.getUserId())
				.bio(userEntity.getBio())
				.dateOfBirth(userEntity.getDateOfBirth())
				.email(userEntity.getEmail())
				.firstName(userEntity.getFirstName())
				.gender(userEntity.getGender())
				.income(userEntity.getIncome())
				.lastName(userEntity.getLastName())
				.phone(userEntity.getPhone())
				.build();
	}
	
	
}
