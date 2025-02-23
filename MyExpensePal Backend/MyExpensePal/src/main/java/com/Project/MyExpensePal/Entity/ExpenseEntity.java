package com.Project.MyExpensePal.Entity;



import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import com.Project.MyExpensePal.Enum.ExpenseType;
import com.Project.MyExpensePal.Enum.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExpenseEntity {

	//Expense name, Expensetype(food, entertainment etc), picture, location, amount, credit/debit, datetime, reciepts
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long expenseId;
	@Column(nullable = false)
	private UUID userId;
	private String expenseName;
	private Integer expense;
	//Add receipt
	@Enumerated(EnumType.STRING)
	private ExpenseType expenseType;
	private String location;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date date = new Date();
	
	@CreationTimestamp
	@Temporal(TemporalType.TIME)
	private Date time = new Date();
	
	//Add receipts
}
