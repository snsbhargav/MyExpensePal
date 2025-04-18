package com.MyExpensePal.AuthenticationService.Entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@ToString
@Builder
public class UserSettingsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID settingsId;
	private int income;
//	private String currency;
	private boolean receiveMonthlyExpenseReport;
//	TODO private boolean notifyOnTransaction;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
	private UserEntity userEntity;
}
