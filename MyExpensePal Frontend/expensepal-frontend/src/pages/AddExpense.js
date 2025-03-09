import React, { useState } from 'react';
import axios from 'axios';
import styles from './AddExpense.module.css';

const AddExpense = () => {
  const [data, setData] = useState({
    userId: "",
    expenseName: "",
    expense: "",
    expenseType: "",
    location: "",
    transactionType: "",
    date: "",
    time: ""
  });

  const { userId, expenseName, expense, expenseType, location, transactionType, date, time } = data;

  const changeHandler = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const submitHandler = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8082/expense/saveExpense", data, {
      headers: {
        "Content-Type": "application/json"
      }
    }).then(response => {
      console.log("data submitted", response.data);
    })
    .catch(error => {
      console.error("Error submitting data", error);
    });
  };

  return (
    <div className='sec222'>
    <div className={styles.expensehtmlForm}>
      <h2>New Expense</h2>
      <hr />
      <form onSubmit={submitHandler}>
        {/* <div className={styles.htmlFormGroup}>
          <label htmlFor="userId">UserId</label>
          <input type="text" id="userId" name='userId' value={userId} onChange={changeHandler} required />
        </div> */}
        <div className={styles.htmlFormGroup}>
          <label htmlFor="expenseName">Expense Name</label>
          <input type="text" id="expenseName" name='expenseName' value={expenseName} onChange={changeHandler} required />
        </div>
        <div className={styles.htmlFormGroup}>
          <label htmlFor="expense">Expense Amount</label>
          <input type="number" id="expense" name='expense' value={expense} onChange={changeHandler} required />
        </div>
        <div className={styles.htmlFormGroup}>
          <label htmlFor="expenseType">Expense Type</label>
          <select id="expenseType" name='expenseType' value={expenseType} onChange={changeHandler}>
            <option value="" disabled>Select Type</option>
            <option value="FOOD">Food</option>
            <option value="ENTERTAINMENT">Entertainment</option>
            <option value="BILLS">Bills</option>
          </select>
        </div>
        <div className={styles.htmlFormGroup}>
          <label htmlFor="location">Location</label>
          <input type="text" id="location" name='location' value={location} onChange={changeHandler} required />
        </div>
        <div className={styles.htmlFormGroup}>
          <label htmlFor="transactionType">Transaction Type</label>
          <select id="transactionType" name='transactionType' value={transactionType} onChange={changeHandler}>
            <option value="" disabled>Select Payment Type</option>
            <option value="DEBIT">Cash</option>
            <option value="CREDIT">Card</option>
          </select>
        </div>
        <div className={styles.htmlFormGroup}>
          <label htmlFor="date">Date</label>
          <input type="date" id="date" name='date' value={date} onChange={changeHandler} />
        </div>
        <div className={styles.htmlFormGroup}>
          <label htmlFor="time">Time</label>
          <input type="time" id="time" name='time' value={time} onChange={changeHandler} />
        </div>
        <div className={styles.buttonGroup}>
          <input type="submit" value="Save" />
        </div>
      </form>
    </div>
      
    </div>

    
  );
};

export default AddExpense;
