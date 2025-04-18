package com.MyExpensePal.AuthenticationService.Dto;

import java.sql.Date;
import java.util.UUID;

import com.MyExpensePal.AuthenticationService.Entity.UserEntity;

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
public class UserSettingsDto {

	private UUID settingsId;
	private int income;
//	private String currency;
	private boolean receiveMonthlyExpenseReport;
}
