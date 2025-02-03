package com.Project.MyExpensePal.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(USER_NOT_FOUND_EXCEPTION.class)
	public ResponseEntity<String> userNotFound(USER_NOT_FOUND_EXCEPTION exception){
		return new ResponseEntity<String>("The user Id you are searching is not in the database.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EXPENSE_ID_NOT_FOUND.class)
	public ResponseEntity<String> expenseNotFound(EXPENSE_ID_NOT_FOUND exception){
		return new ResponseEntity<String>("The expense Id you are searching is not in the database.", HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NO_USER_EXPENSES_FOUND_EXCEPTION.class)
	public ResponseEntity<String> userExpenseNotFound(NO_USER_EXPENSES_FOUND_EXCEPTION exception){
		return new ResponseEntity<String>("No expenses found in the database of user id.", HttpStatus.NOT_FOUND);
	}
}
