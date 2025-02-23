package com.MyExpensePal.AuthenticationService.Dto;

import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserDto {

	private UUID userId;
	private String firstName;
	private String lastName;
	private String email; // Used as username
	private String gender;
	private Date dateOfBirth;
	private String bio;
	private String phone;
	
	
}
