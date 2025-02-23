package com.Project.MyExpensePal.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.MyExpensePal.Entity.LimitsEntity;

@Repository
public interface LimitsRepository extends JpaRepository<LimitsEntity, UUID>{

}
