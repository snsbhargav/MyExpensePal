import React, { useEffect, useState } from 'react'
import '../styles/Dashboard.css';
import axios from 'axios';
import {Pie, Bar} from 'react-chartjs-2';
import{Chart as ChartJs, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend,ArcElement} from 'chart.js';

// Register Chart.js components
ChartJs.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  ArcElement
);

const Dashboard = () => {
  // console.log(expenses);
  const [expenses, setExpenses] = useState([]);
  const [foodTotal, setFoodTotal] = useState(0);
  const [billsTotal, setBillsTotal] = useState(0);
  const [entertainmentTotal, setEntertainmentTotal] = useState(0);
  const [monthlyData, setMonthlyData] = useState({});
  const [selectedYear, setSelectedYear] = useState(null);
  
  
  useEffect(() => {
    const userId = localStorage.getItem("userId"); 
    const token = localStorage.getItem("token");
    const fetchExpenseTotal = async (expenseType, setTotal) => {
      try {
        const response = await axios.get(`http://localhost:8080/expense/calculateTotalSumOfExpenseType/${expenseType}`, {
          
          headers: {
            Authorization: `Bearer ${token}`,
            'userId': userId
          }
        });
        setTotal(response.data); 
      } catch (error) {
        console.error(`Error fetching ${expenseType} total:`, error);
      }
    };
    // Fetch totals for each category
    fetchExpenseTotal("FOOD", setFoodTotal);
    fetchExpenseTotal("BILLS", setBillsTotal);
    fetchExpenseTotal("ENTERTAINMENT", setEntertainmentTotal);

    const fetchExpenses = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/expense/userId`, {
          headers: {
            Authorization: `Bearer ${token}`,
            'UserId': userId
          }
        });
        setExpenses(response.data);
      } catch (error) {
        console.error("Error fetching expenses:", error);
      }
    };
    fetchExpenses();
  }, []);
  useEffect(() => {
    // console.log(expenses.date);
    if (expenses && expenses.length > 0) {
      let expenseMap = {};

      // Group expenses by year and month
      expenses.forEach(expense => {
        const date = new Date(expense.date);
        const year = date.getFullYear();
        const month = date.getMonth();

        if (!expenseMap[year]) {
          expenseMap[year] = Array(12).fill(0); // Initialize an array for the months
        }
        expenseMap[year][month] += expense.expense;
      });

      setMonthlyData(expenseMap);
      const latestYear = Math.max(...Object.keys(expenseMap).map(Number)); //Set the latest year as the default
      setSelectedYear(latestYear.toString());// Set the latest year as the default
    }
  }, [expenses]);
  const generateChartData = (year) => {
    const labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    const datasets = [];

    if (year && monthlyData[year]) {
      datasets.push({
        label: `Expenses for ${year}`,
        data: monthlyData[year],
        backgroundColor: '#4caf50',
        borderRadius: 10,
        borderWidth: 1,
      });
    }
  
    return { labels, datasets };
  };
  const handleYearChange = (event) => {
    setSelectedYear(event.target.value);
  };

  const years = Object.keys(monthlyData);


 
  
  return (
    <div className='d-main'>
      
      <div className='d-main1'>
        <div className='d-one'>
          <div className='food'>
            <h4>Food</h4>
            <p>Total: ${foodTotal}</p>
           
          </div>
          <div className='bill'>
            <h4>Bills</h4>
            <p>Total: ${billsTotal}</p>

          </div>
          <div className='enter'>
            <h4>Entertainment</h4>
            <p>Total: ${entertainmentTotal}</p>            
          </div>
          
        </div>
      
        <div className='d-two'>
          {/* <Bar data={generateChartData()} /> */}
          <select onChange={handleYearChange} value={selectedYear}>
            <option value="">Select Year</option>
            {years.map(year => (
              <option key={year} value={year}>{year}</option>
            ))}
          </select>

          {selectedYear ? (
            <Bar data={generateChartData(selectedYear)} />
          ) : (
             <div>Select a year to view the expenses breakdown.</div>
          )}
          
          
        </div>
      </div>
      <div className='d-three'>
        <h3>Latest 10 Expenses</h3>
      <p>ssdxsxsxsdxs</p>
      <p>ssdxsxsxsdxs</p>
        <p>ssdxshhhhhhhhhxsxsdxs</p>
        <p>ssdxsxsxsdxs</p>
        <p>ssdxsxsxsdxs</p>
      <p>ssdxsxsxsdxs</p>
        <p>ssdxshhhhhhhhhxsxsdxs</p>
        <p>ssdxsxsxsdxs</p>
        
      </div>
      
      
    </div>
  )
}

export default Dashboard
