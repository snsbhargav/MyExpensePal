package com.Project.MyExpensePal.ServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Project.MyExpensePal.Entity.ExpenseEntity;
import com.Project.MyExpensePal.Enum.ExpenseType;
import com.Project.MyExpensePal.Exception.EXPENSE_ID_NOT_FOUND;
import com.Project.MyExpensePal.Exception.NO_USER_EXPENSES_FOUND_EXCEPTION;
import com.Project.MyExpensePal.Repository.ExpensesRepository;
import com.Project.MyExpensePal.Service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpensesRepository expensesRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<String> saveExpenseToDatabase(String userId, ExpenseEntity expenseEntity) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>(header);
		
		if (!restTemplate.exchange("lb://AUTHENTICATION-SERVICE/auth/isUserInDatabase", HttpMethod.GET, entity, Boolean.class).getBody()) {
			return new ResponseEntity<String>("User not found in the database", HttpStatus.NOT_FOUND);
		}
		//Add userId to the entity
		expenseEntity.setUserId(UUID.fromString(userId));
		//Check if expenseType is null
		if(expenseEntity.getExpenseType() == null)
			expenseEntity.setExpenseType(ExpenseType.UNCATEGORIZED);
		expensesRepository.save(expenseEntity);
		return new ResponseEntity<String>("Expense Saved successfully.", HttpStatus.CREATED);
	}

	public ResponseEntity<ExpenseEntity> retreiveExpenseByExpenseId(UUID expenseId) {
		ExpenseEntity expenseEntity = expensesRepository.findById(expenseId)
				.orElseThrow(() -> new EXPENSE_ID_NOT_FOUND());
		if (expenseEntity == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		return new ResponseEntity<ExpenseEntity>(expenseEntity, HttpStatus.OK);
	}

	public ResponseEntity<List<ExpenseEntity>> retreiveExpenseByUserId(UUID userId)
			throws NO_USER_EXPENSES_FOUND_EXCEPTION {
		List<ExpenseEntity> expenseEntities = expensesRepository.findByUserId(userId);
		if (expenseEntities.size() == 0)
			throw new NO_USER_EXPENSES_FOUND_EXCEPTION();

		return new ResponseEntity<List<ExpenseEntity>>(expenseEntities, HttpStatus.OK);
	}

	public ResponseEntity<String> updateExpense(UUID expenseId, ExpenseEntity expenseEntity) {
		ExpenseEntity actualExpense = retreiveExpenseByExpenseId(expenseId).getBody();
		actualExpense.setExpenseName(expenseEntity.getExpenseName());
		actualExpense.setExpense(expenseEntity.getExpense());
		actualExpense.setLocation(expenseEntity.getLocation());
		actualExpense.setDate(expenseEntity.getDate());
		actualExpense.setTime(expenseEntity.getTime());
		actualExpense.setExpenseType(expenseEntity.getExpenseType());
		actualExpense.setTransactionType(expenseEntity.getTransactionType());

		expensesRepository.save(actualExpense);
		return new ResponseEntity<String>("Update successful", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteExpense(UUID expenseId) {
		expensesRepository.deleteById(expenseId);
		return new ResponseEntity<String>("Expense deleted successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ExpenseEntity>> tenLatestTransactions(UUID userId)
			throws NO_USER_EXPENSES_FOUND_EXCEPTION {
		List<ExpenseEntity> expenseEntities = expensesRepository.tenLatestTransactions(userId);
		if (expenseEntities.size() == 0)
			throw new NO_USER_EXPENSES_FOUND_EXCEPTION();
		return new ResponseEntity<List<ExpenseEntity>>(expenseEntities, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> findTotalBasedOnExpenseType(UUID userId, String expenseType) {
		Integer totalExpensesTypeAmount = expensesRepository.expensesTotalBasedOnExpenseType(userId, expenseType);
		if (totalExpensesTypeAmount == null)
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		return new ResponseEntity<Integer>(totalExpensesTypeAmount, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Map<String, Integer>>> getTopThreeCategoriesOfMonth(UUID userId, String fromDate, String toDate) {
		//Takes Current month if no start date is provided
		LocalDate currentDate = LocalDate.now();
		LocalDate defaultStartDate = currentDate.withDayOfMonth(1);
		LocalDate defaultEndDate = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
		LocalDate startDate, endDate; 
		if(fromDate == null) {
			startDate = defaultStartDate;
			endDate = defaultEndDate;
		} else if(toDate == null) {
			startDate = LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE);
			endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		} else {
			startDate = LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE);
			endDate = LocalDate.parse(toDate, DateTimeFormatter.ISO_DATE);
		}
		
		List<Map<String, Integer>> categoryTotals = expensesRepository.getTopThreeCategoriesOfMonth(userId, startDate, endDate);
		return new ResponseEntity<List<Map<String, Integer>>>(categoryTotals, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> deleteAllExpensesOfUser(UUID userId) {
		expensesRepository.deleteExpenseByUserId(userId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
