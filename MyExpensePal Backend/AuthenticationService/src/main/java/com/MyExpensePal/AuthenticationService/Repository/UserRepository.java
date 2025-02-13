package com.MyExpensePal.AuthenticationService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MyExpensePal.AuthenticationService.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
