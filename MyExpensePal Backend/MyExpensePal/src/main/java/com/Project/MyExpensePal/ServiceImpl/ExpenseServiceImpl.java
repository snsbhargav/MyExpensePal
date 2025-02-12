package com.Project.MyExpensePal.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Project.MyExpensePal.Entity.ExpensesEntity;
import com.Project.MyExpensePal.Exception.EXPENSE_ID_NOT_FOUND;
import com.Project.MyExpensePal.Exception.NO_USER_EXPENSES_FOUND_EXCEPTION;
import com.Project.MyExpensePal.Model.ExpensesModel;
import com.Project.MyExpensePal.Repository.ExpensesRepository;
import com.Project.MyExpensePal.Service.ExpenseService;



@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpensesRepository expensesRepository;

	@Override
	public ResponseEntity<String> saveExpenseToDatabase(ExpensesModel expensesModel) {

		expensesRepository.save(ExpensesModel.expenseModelToEntity(expensesModel));
		return new ResponseEntity<String>("Expense Saved successfully.", HttpStatus.CREATED);
	}

	public ResponseEntity<ExpensesModel> retreiveExpenseByExpenseId(Long expenseId) {
		ExpensesEntity expenseEntity = expensesRepository.findById(expenseId)
				.orElseThrow(() -> new EXPENSE_ID_NOT_FOUND());
		if (expenseEntity == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		return new ResponseEntity<ExpensesModel>(ExpensesModel.expenseEntityToModel(expenseEntity), HttpStatus.FOUND);
	}

	public ResponseEntity<List<ExpensesModel>> retreiveExpenseByUserId(Long userId)
			throws NO_USER_EXPENSES_FOUND_EXCEPTION {
		List<ExpensesEntity> expenseEntities = expensesRepository.findByUserId(userId);
		List<ExpensesModel> expensesModels = new ArrayList<>();
		for (ExpensesEntity expenses : expenseEntities)
			expensesModels.add(ExpensesModel.expenseEntityToModel(expenses));

		if (expenseEntities.size() == 0)
			throw new NO_USER_EXPENSES_FOUND_EXCEPTION();

		return new ResponseEntity<List<ExpensesModel>>(expensesModels, HttpStatus.FOUND);
	}

	public ResponseEntity<String> updateExpense(ExpensesModel expensesModel) {
		// TODO Auto-generated method stub
		expensesRepository.save(ExpensesModel.expenseModelToEntity(expensesModel));
		return new ResponseEntity<String>("Update successful", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteExpense(Long expenseId) {
		expensesRepository.deleteById(expenseId);
		return new ResponseEntity<String>("Expense deleted successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ExpensesModel>> tenLatestTransactions(Long userId)
			throws NO_USER_EXPENSES_FOUND_EXCEPTION {
		List<ExpensesEntity> expenseEntities = expensesRepository.tenLatestTransactions(userId);
		List<ExpensesModel> expensesModels = new ArrayList<>();
		for (ExpensesEntity expenses : expenseEntities)
			expensesModels.add(ExpensesModel.expenseEntityToModel(expenses));
		if (expenseEntities.size() == 0)
			throw new NO_USER_EXPENSES_FOUND_EXCEPTION();
		return new ResponseEntity<List<ExpensesModel>>(expensesModels, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> findTotalBasedOnExpenseType(Long userId, String expenseType) {
		Integer totalExpensesTypeAmount = expensesRepository.expensesTotalBasedOnExpenseType(userId, expenseType);
		if (totalExpensesTypeAmount == null)
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		return new ResponseEntity<Integer>(totalExpensesTypeAmount, HttpStatus.OK);
	}

}
