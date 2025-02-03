package com.Project.MyExpensePal.Entity;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<ExpensesEntity> expenses;
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<LimitsEntity> limits;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	private Date dateOfBirth;
	private String bio;
	private String phone;
	private int income;
//	@Lob
//	private byte[] picture;
	//Add for profile picture
	
	
}
