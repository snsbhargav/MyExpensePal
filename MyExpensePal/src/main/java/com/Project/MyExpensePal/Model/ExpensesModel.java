package com.Project.MyExpensePal.Model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.Project.MyExpensePal.Entity.ExpensesEntity;
import com.Project.MyExpensePal.Entity.UserEntity;
import com.Project.MyExpensePal.Enum.ExpenseType;
import com.Project.MyExpensePal.Enum.TransactionType;
import com.Project.MyExpensePal.ServiceImpl.ExpenseServiceImpl;

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
	
	private Long expenseId;
	private Long userId;
	private String expenseName;
	private Integer expense;
	private ExpenseType expenseType;
	private String location;
	private TransactionType transactionType;
	private Date date;
	private Date time;
	
	
	public static ExpensesModel expenseEntityToModel(ExpensesEntity expenseEntity) {
		return ExpensesModel.builder()
				.expenseId(expenseEntity.getExpenseId())
				.userId(expenseEntity.getUser().getUserId())
				.transactionType(expenseEntity.getTransactionType())
				.location(expenseEntity.getLocation())
				.expenseType(expenseEntity.getExpenseType())
				.expenseName(expenseEntity.getExpenseName())
				.date(expenseEntity.getDate())
				.time(expenseEntity.getTime())
				.expense(expenseEntity.getExpense())
				.build();
	}
	

	public static ExpensesEntity expenseModelToEntity(ExpensesModel expensesModel) {
		
		return ExpensesEntity.builder()
				.expenseId(expensesModel.getExpenseId())
				.user(UserEntity.builder()
						.userId(expensesModel.getUserId())
						.build())
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
