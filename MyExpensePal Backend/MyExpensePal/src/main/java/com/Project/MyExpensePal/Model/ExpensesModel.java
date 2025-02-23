package com.Project.MyExpensePal.Model;

import java.util.Date;
import java.util.UUID;

import com.Project.MyExpensePal.Entity.ExpenseEntity;
import com.Project.MyExpensePal.Enum.ExpenseType;
import com.Project.MyExpensePal.Enum.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExpensesModel {
	
	private UUID expenseId;
	private UUID userId;
	private String expenseName;
	private Integer expense;
	private ExpenseType expenseType;
	private String location;
	private TransactionType transactionType;
	private Date date;
	private Date time;
	
	
	public static ExpensesModel expenseEntityToModel(ExpenseEntity expenseEntity) {
		return ExpensesModel.builder()
				.expenseId(expenseEntity.getExpenseId())
				.userId(expenseEntity.getUserId())
				.transactionType(expenseEntity.getTransactionType())
				.location(expenseEntity.getLocation())
				.expenseType(expenseEntity.getExpenseType())
				.expenseName(expenseEntity.getExpenseName())
				.date(expenseEntity.getDate())
				.time(expenseEntity.getTime())
				.expense(expenseEntity.getExpense())
				.build();
	}
	

	public static ExpenseEntity expenseModelToEntity(ExpensesModel expensesModel) {
		
		return ExpenseEntity.builder()
				.expenseId(expensesModel.getExpenseId())
				.userId(expensesModel.getUserId())
				.transactionType(expensesModel.getTransactionType())
				.location(expensesModel.getLocation())
				.expenseType(expensesModel.getExpenseType())
				.expenseName(expensesModel.getExpenseName())
				.date(expensesModel.getDate())
				.time(expensesModel.getTime())
				.expense(expensesModel.getExpense())
				.build();
	}

}
