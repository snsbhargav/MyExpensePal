package com.MyExpensePal.ReportGenerationService.Model;

import java.util.Date;
import java.util.UUID;


public class ExpensesModel {
	
	public ExpensesModel() {
		// TODO Auto-generated constructor stub
	}
	
	private Long expenseId;
	private UUID userId;
	private String expenseName;
	private Integer expense;
	private ExpenseType expenseType;
	private String location;
	private Date date;
	private Date time;
	
	public ExpensesModel(Long expenseId, UUID userId, String expenseName, Integer expense, ExpenseType expenseType,
			String location, Date date, Date time) {
		super();
		this.expenseId = expenseId;
		this.userId = userId;
		this.expenseName = expenseName;
		this.expense = expense;
		this.expenseType = expenseType;
		this.location = location;
		this.date = date;
		this.time = time;
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public Integer getExpense() {
		return expense;
	}

	public void setExpense(Integer expense) {
		this.expense = expense;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ExpensesModel [expenseId=" + expenseId + ", userId=" + userId + ", expenseName=" + expenseName
				+ ", expense=" + expense + ", expenseType=" + expenseType + ", location=" + location + ", date=" + date
				+ ", time=" + time + "]";
	}
	
	



	
	
	
	
	
}
