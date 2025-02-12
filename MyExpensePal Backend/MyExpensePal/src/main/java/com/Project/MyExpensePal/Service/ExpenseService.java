package com.Project.MyExpensePal.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Project.MyExpensePal.Exception.NO_USER_EXPENSES_FOUND_EXCEPTION;
import com.Project.MyExpensePal.Model.ExpensesModel;



@Service
public interface ExpenseService {

	public ResponseEntity<String> saveExpenseToDatabase(ExpensesModel expensesModel);

	public ResponseEntity<ExpensesModel> retreiveExpenseByExpenseId(Long expenseId);

	public ResponseEntity<List<ExpensesModel>> retreiveExpenseByUserId(Long userId) throws NO_USER_EXPENSES_FOUND_EXCEPTION;

	public ResponseEntity<String> updateExpense(ExpensesModel expensesModel);

	public ResponseEntity<String> deleteExpense(Long expenseId);

	public ResponseEntity<List<ExpensesModel>> tenLatestTransactions(Long userId) throws NO_USER_EXPENSES_FOUND_EXCEPTION;

	public ResponseEntity<Integer> findTotalBasedOnExpenseType(Long userId, String expenseType);
}
