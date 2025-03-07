package com.Project.MyExpensePal.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Project.MyExpensePal.Entity.ExpenseEntity;
import com.Project.MyExpensePal.Exception.NO_USER_EXPENSES_FOUND_EXCEPTION;



@Service
public interface ExpenseService {

	public ResponseEntity<String> saveExpenseToDatabase(ExpenseEntity expensesModel);

	public ResponseEntity<ExpenseEntity> retreiveExpenseByExpenseId(UUID expenseId);

	public ResponseEntity<List<ExpenseEntity>> retreiveExpenseByUserId(UUID userId) throws NO_USER_EXPENSES_FOUND_EXCEPTION;

	public ResponseEntity<String> updateExpense(UUID expenseId, ExpenseEntity expensesModel);

	public ResponseEntity<String> deleteExpense(UUID expenseId);

	public ResponseEntity<List<ExpenseEntity>> tenLatestTransactions(UUID userId) throws NO_USER_EXPENSES_FOUND_EXCEPTION;

	public ResponseEntity<Integer> findTotalBasedOnExpenseType(UUID userId, String expenseType);
}
