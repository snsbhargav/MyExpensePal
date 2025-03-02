package com.MyExpensePal.AuthenticationService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(USER_NOT_FOUND_EXCEPTION.class)
	public ResponseEntity<String> expenseNotFound(USER_NOT_FOUND_EXCEPTION exception){
		return new ResponseEntity<String>("The User you are searching is not in the database.", HttpStatus.NOT_FOUND);
	}
	
}
