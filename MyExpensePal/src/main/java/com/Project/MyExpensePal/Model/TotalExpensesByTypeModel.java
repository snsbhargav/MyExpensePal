package com.Project.MyExpensePal.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TotalExpensesByTypeModel {

	String expenseType;
	Integer totalAmount;

}
