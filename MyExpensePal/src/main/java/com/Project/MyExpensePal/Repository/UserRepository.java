package com.Project.MyExpensePal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.MyExpensePal.Entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
