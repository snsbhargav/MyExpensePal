package com.MyExpensePal.AuthenticationService.Entity;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID userId;
	private String firstName;
	private String lastName;
	private String email; // Used as username
	private String password;
	private String gender;
	private Date dateOfBirth;
	private String bio;
	private String phone;
//	TODO Add for profile picture

}
