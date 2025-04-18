package com.MyExpensePal.AuthenticationService.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MyExpensePal.AuthenticationService.Entity.UserSettingsEntity;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettingsEntity, UUID>{

	@Query(nativeQuery = true, value = "SELECT * FROM USER_SETTINGS_ENTITY WHERE USER_ID=?")
	UserSettingsEntity findByUserId(UUID userId);

}
