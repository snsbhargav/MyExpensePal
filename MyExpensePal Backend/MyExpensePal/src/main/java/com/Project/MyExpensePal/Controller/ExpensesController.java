package com.Project.MyExpensePal.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.Project.MyExpensePal.Entity.ExpenseEntity;
import com.Project.MyExpensePal.Exception.NO_USER_EXPENSES_FOUND_EXCEPTION;
import com.Project.MyExpensePal.Service.ExpenseService;

@RestController
@RequestMapping("/expense/")
public class ExpensesController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/saveExpense")
	public ResponseEntity<String> saveExpense(@RequestBody ExpenseEntity expensesEntity) {
		return expenseService.saveExpenseToDatabase(expensesEntity);
	}

	@GetMapping("/expenseId/{expenseId}")
	public ResponseEntity<ExpenseEntity> getExpenseByExpenseId(@PathVariable("expenseId") UUID expenseId) {
		return expenseService.retreiveExpenseByExpenseId(expenseId);
	}

	
	@GetMapping("/userId")
	public ResponseEntity<List<ExpenseEntity>> getExpenseByUserId(@RequestHeader("userId") String userId)
			throws NO_USER_EXPENSES_FOUND_EXCEPTION {
		return expenseService.retreiveExpenseByUserId(UUID.fromString(userId));
	}

	@PutMapping("/updateExpense/{expenseId}")
	public ResponseEntity<String> updateExpense(@PathVariable UUID expenseId,
			@RequestBody ExpenseEntity expensesModel) {
		return expenseService.updateExpense(expenseId, expensesModel);
	}

	@DeleteMapping("/deleteExpense/{expenseId}")
	public ResponseEntity<String> deleteExpense(@PathVariable("expenseId") UUID expenseId) {
		return expenseService.deleteExpense(expenseId);
	}

	@GetMapping("/tenLatestTransactions")
	public ResponseEntity<List<ExpenseEntity>> retrieveTenLatestTransactions(@RequestHeader("userId") UUID userId)
			throws NO_USER_EXPENSES_FOUND_EXCEPTION {
		return expenseService.tenLatestTransactions(userId);
	}

	@GetMapping("/calculateTotalSumOfExpenseType/{expenseType}")
	public ResponseEntity<Integer> findTotalAmountBasedOnExpenseType(@RequestHeader("userId") UUID userId,
			@PathVariable("expenseType") String expenseType) {
		return expenseService.findTotalBasedOnExpenseType(userId, expenseType);
	}
}
