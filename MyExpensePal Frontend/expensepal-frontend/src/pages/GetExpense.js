import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/GetExpense.css';
import { useNavigate } from 'react-router-dom';

const GetExpense = () => {
    const [expenses, setExpenses] = useState([]);
    const [error, setError] = useState(null);
    const [isModalOpen, setIsModalOpen]=useState(false);
    const [selectedExpense, setSelectedExpense]=useState(null);
    const navigate=useNavigate();

    useEffect(() => {
        const fetchExpenses = async () => {
            const userId = "41ca3afa-a016-46b2-b1fd-4b1d8ac1dc96";
            const token = localStorage.getItem("token"); 

            try {
                const response = await axios.get(`http://localhost:8082/expense/userId`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'UserId': userId
                    }
                });
                setExpenses(response.data);
                //console.log(response.data)
            } catch (error) {
                console.error("Error fetching expenses:", error);
                setError("Failed to fetch expenses.");
            }
        };

        fetchExpenses();
    }, []);
    const handleDelete =  (expenseId) => {
        console.log("Deleting expense:", expenseId);
        const token = localStorage.getItem("token");
        try{
            axios.delete(`http://localhost:8082/expense/deleteExpense/${expenseId}`,{
                headers: {Authorization: `Bearer ${token}`}
            });
            setExpenses(expenses.filter(expense => expense.expenseId !== expenseId));
        }catch(error){
            console.error("Error deleting expense:", error);            
            setError("Failed")
        }
        
    };
    const handleEdit = (expense) => {
        // setSelectedExpense(expense); 
        navigate('/updateexpense',{state:{expense}});
    };
    const handleView =(expense)=>{
        setSelectedExpense(expense);
        setIsModalOpen(true);
        //navigate('/viewexpense',{state:{expense}});
    };
    const closeModal=()=>{
        setIsModalOpen(false);
    }

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
                                <button className="view-btn" onClick={() => handleView(expense)}>View</button>
                                <button className="edit-btn" onClick={() => handleEdit(expense)}>Edit</button>
                                <button className="delete-btn" onClick={() => handleDelete(expense.expenseId)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {/*displaying single expense */}
            {isModalOpen && selectedExpense &&(
                <div className='modal'>
                    <div className='modal-content' style={{fontSize:"19px",color:"white"}}>
                        <span className='close-btn' onClick={closeModal}>&times;</span>
                        <h2>Expense Details</h2>
                        <p><strong>Expense Name:</strong> {selectedExpense.expenseName}</p>
                        <p><strong>Amount:</strong> ${selectedExpense.expense}</p>
                        <p><strong>Expense Type:</strong> {selectedExpense.expenseType}</p>
                        <p><strong>Transaction Type:</strong> {selectedExpense.transactionType}</p>
                        <p><strong>Location:</strong> {selectedExpense.location}</p>
                        <p><strong>Created On:</strong> {selectedExpense.createdOn}</p>
                        {/*<p><strong>Time:</strong> {selectedExpense.time}</p>*/}
                        <p><strong>Last Modified:</strong> {selectedExpense.lastModified}</p>
                        <div>
                            <button className='edit-btn' onClick={() => handleEdit(selectedExpense.expense)}>Edit</button>
                            <button className='delete-btn' onClick={() => handleDelete(selectedExpense.expenseId)}>Delete</button>
                            <button>Download</button>
                            <button>Send Email</button>
                        </div>
                    </div>
                </div>
            )}
        </section>
    );
};

export default GetExpense;
