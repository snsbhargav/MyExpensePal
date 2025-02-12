package com.Project.MyExpensePal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Project.MyExpensePal.Entity.ExpensesEntity;


@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM EXPENSES_ENTITY WHERE USER_ID=?")
	List<ExpensesEntity> findByUserId(Long userId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM expenses_tracker_app.expenses_entity where user_id=? ORDER BY date desc, time desc LIMIT 10;")
	List<ExpensesEntity> tenLatestTransactions(Long userId);
	
	@Query(nativeQuery = true, value = "select sum(expense) from expenses_tracker_app.expenses_entity where user_id=? and expense_type=?;")	
	Integer expensesTotalBasedOnExpenseType(Long userId, String expenseType);
}
