package com.Project.MyExpensePal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.MyExpensePal.Entity.LimitsEntity;

@Repository
public interface LimitsRepository extends JpaRepository<LimitsEntity, Long>{

}
