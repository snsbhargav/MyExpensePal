package com.MyExpensePal.AuthenticationService.Mappers;

import com.MyExpensePal.AuthenticationService.Dto.UserSettingsDto;
import com.MyExpensePal.AuthenticationService.Entity.UserSettingsEntity;

public class SettingsMapper {
	
	public static UserSettingsEntity DtoToEntity(UserSettingsDto dto) {
		return UserSettingsEntity.builder()
				.income(dto.getIncome())
				.receiveMonthlyExpenseReport(dto.isReceiveMonthlyExpenseReport())
				.build();
	}
	
	public static UserSettingsDto EntityToDto(UserSettingsEntity entity) {
		return UserSettingsDto.builder()
				.income(entity.getIncome())
				.settingsId(entity.getSettingsId())
				.receiveMonthlyExpenseReport(entity.isReceiveMonthlyExpenseReport())
				.build();
	}

}
