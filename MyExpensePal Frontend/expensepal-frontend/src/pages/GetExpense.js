import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './GetExpense.css';
import { useNavigate } from 'react-router-dom';

const GetExpense = () => {
    const [expenses, setExpenses] = useState([]);
    const [error, setError] = useState(null);
    const navigate=useNavigate();

    useEffect(() => {
        const fetchExpenses = async () => {
            const userId = "918859ce-4e48-4532-97a4-59e454be732d";
            const token = localStorage.getItem("token"); 

            try {
                const response = await axios.get(`http://localhost:8082/expense/userId/${userId}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                setExpenses(response.data);
            } catch (error) {
                console.error("Error fetching expenses:", error);
                setError("Failed to fetch expenses.");
            }
        };

        fetchExpenses();
    }, []);

  

    const handleDelete = (expenseId) => {
        console.log("Deleting expense:", expenseId);
        
    };
    const handleEdit = (expense) => {
        // setSelectedExpense(expense); 
        navigate('/updateExpense',{state:{expense}});
    };

    const SortedExpenses = ()=>{
        return [...expenses].sort((a,b)=>new Date(b.date)- new Date(a.date));
    }
    

    return (
        <section id="sec22">
            <h2>Your Expenses</h2>
            {error && <p className="error">{error}</p>}

            <table className="expense-table">
                <thead>
                    <tr>
                        <th>Expense Name</th>
                        <th>Amount</th>
                        <th>Type</th>
                        <th>Location</th>
                        <th>Transaction Type</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {SortedExpenses().map((expense) => (
                        <tr key={expense.expenseId}>
                            <td>{expense.expenseName}</td>
                            <td>${expense.expense}</td>
                            <td>{expense.expenseType}</td>
                            <td>{expense.location}</td>
                            <td>{expense.transactionType}</td>
                            <td>{expense.date}</td>
                            <td>{expense.time}</td>
                            <td>
                    
                                <button className="edit-btn" onClick={() => handleEdit(expense)}>Edit</button>
                                <button className="delete-btn" onClick={() => handleDelete(expense.expense_id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </section>
    );
};

export default GetExpense;
