package com.MyExpensePal.AuthenticationService.Contoller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyExpensePal.AuthenticationService.Dto.UserSettingsDto;
import com.MyExpensePal.AuthenticationService.Exception.ENTITY_FAILED_TO_SAVE_EXCEPTION;
import com.MyExpensePal.AuthenticationService.Exception.USER_NOT_FOUND_EXCEPTION;
import com.MyExpensePal.AuthenticationService.Service.SettingsService;

@RestController
@RequestMapping("/auth")
public class SettingsController {

	@Autowired
	private SettingsService settingsService;

	//No need for separate API. default setting will be set to every user.
	@PostMapping("/saveSettings")
	public ResponseEntity<UserSettingsDto> saveSettings(@RequestHeader("userId") String userId,
			@RequestBody UserSettingsDto settingsEntity)
			throws USER_NOT_FOUND_EXCEPTION, ENTITY_FAILED_TO_SAVE_EXCEPTION {
		return settingsService.saveSettings(UUID.fromString(userId), settingsEntity);
	}

	@GetMapping("/getSettings")
	public ResponseEntity<UserSettingsDto> getSettings(@RequestHeader("userId") String userId) {
		return settingsService.getSettingsByUserId(UUID.fromString(userId));
	}

	@PutMapping("/updateSettings")
	public ResponseEntity<UserSettingsDto> updateSettings(@RequestHeader("userId") String userId,
			@RequestBody UserSettingsDto newUserSettings) throws ENTITY_FAILED_TO_SAVE_EXCEPTION {
		return settingsService.updateSettings(UUID.fromString(userId), newUserSettings);
	}

}
