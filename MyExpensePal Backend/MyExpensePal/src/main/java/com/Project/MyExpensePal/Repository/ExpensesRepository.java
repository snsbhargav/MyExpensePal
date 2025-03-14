package com.Project.MyExpensePal.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Project.MyExpensePal.Entity.ExpenseEntity;


@Repository
public interface ExpensesRepository extends JpaRepository<ExpenseEntity, UUID> {

	@Query(nativeQuery = true, value = "SELECT * FROM EXPENSE_ENTITY WHERE USER_ID=?")
	List<ExpenseEntity> findByUserId(UUID userId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM my_expense_pal.expense_entity where user_id=? ORDER BY date desc, time desc LIMIT 10;")
	List<ExpenseEntity> tenLatestTransactions(UUID userId);
	
	@Query(nativeQuery = true, value = "select sum(expense) from my_expense_pal.expense_entity where user_id=? and expense_type=?;")	
	Integer expensesTotalBasedOnExpenseType(UUID userId, String expenseType);
}
