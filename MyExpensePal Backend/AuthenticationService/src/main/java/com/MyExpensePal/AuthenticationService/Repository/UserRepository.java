package com.MyExpensePal.AuthenticationService.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MyExpensePal.AuthenticationService.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{

	@Query(value = "SELECT * FROM USER_ENTITY WHERE EMAIL=?", nativeQuery = true)
	UserEntity findByEmail(String email);

}
