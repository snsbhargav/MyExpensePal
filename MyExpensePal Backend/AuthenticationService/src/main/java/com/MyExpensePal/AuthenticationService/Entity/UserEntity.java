package com.MyExpensePal.AuthenticationService.Entity;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email; // Used as username
	@Column(nullable = false)
	private String password;
	private String gender;
	private Date dateOfBirth;
	private String bio;
	private String phone;
//	TODO Add for profile picture
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true, mappedBy = "userEntity")
	private UserSettingsEntity settings;

}
