package com.MyExpensePal.AuthenticationService.Service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MyExpensePal.AuthenticationService.Dto.UserSettingsDto;
import com.MyExpensePal.AuthenticationService.Exception.ENTITY_FAILED_TO_SAVE_EXCEPTION;
import com.MyExpensePal.AuthenticationService.Exception.USER_NOT_FOUND_EXCEPTION;

@Service
public interface SettingsService {

	ResponseEntity<UserSettingsDto> saveSettings(UUID fromString, UserSettingsDto settingsDto)
			throws USER_NOT_FOUND_EXCEPTION, ENTITY_FAILED_TO_SAVE_EXCEPTION;

	ResponseEntity<UserSettingsDto> getSettingsByUserId(UUID userId);

	ResponseEntity<UserSettingsDto> updateSettings(UUID userId, UserSettingsDto newUserSettings)
			throws ENTITY_FAILED_TO_SAVE_EXCEPTION;

}
