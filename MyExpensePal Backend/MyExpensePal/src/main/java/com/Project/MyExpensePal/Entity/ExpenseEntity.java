package com.Project.MyExpensePal.Entity;



import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import jakarta.persistence.PrePersist;
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
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID expenseId;
	@Column(nullable = false)
	private UUID userId;
	@Column(nullable = false)
	private String expenseName;
	@Column(nullable = false)
	private Integer expense;
	//Add receipt
	@Enumerated(EnumType.STRING)
	private ExpenseType expenseType;
	private String location;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@Temporal(TemporalType.DATE)
	private LocalDate date;
	
	@Temporal(TemporalType.TIME)
	private LocalTime time;
	
	@CreationTimestamp
	private Date createdOn;
	
	@UpdateTimestamp
	private Date lastModified;
	
	@PrePersist
    protected void onCreate() {
        if (this.date == null) {
            this.date = LocalDate.now(ZoneId.of("UTC")); // Set UTC time zone
        }
        if (this.time == null) {
            this.time = LocalTime.now(ZoneId.of("UTC"));
        }
    }
	
	
	
	//Add receipts
}
