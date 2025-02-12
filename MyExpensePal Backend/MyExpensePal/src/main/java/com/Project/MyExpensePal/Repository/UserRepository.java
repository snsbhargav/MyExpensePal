package com.Project.MyExpensePal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Project.MyExpensePal.Entity.UserEntity;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	@Query(value = "SELECT * FROM user_entity WHERE email=?;",nativeQuery = true)
	public UserEntity findUserByEmail(String email);

}
