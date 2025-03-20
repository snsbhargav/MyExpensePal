import React from "react";
import { Link } from "react-router-dom";
import "../App.css";


const Navbar = () => {
  return (
    <div>
        <h2 style={{textAlign:"center"}}>MyExpensePal</h2>
        <div className="logo" >            
            <img src="/images/spending.png" alt="User" /> 
        </div>
        <div>
        <ul>
          <li><Link to="/dashboard">Dashboard</Link></li>
          <li><Link to="/addexpense">Log an Expense</Link></li>
          <li><Link to="/getexpense">My Expenses</Link></li>
          <li><Link to="/statistics">Statistics</Link></li>
          <li><Link to="/chat">Chat with AI</Link></li>
          <li><Link to="/settings">Settings</Link></li>
          <li><Link to="/aboutus">About Us</Link></li>     
        </ul>  </div>      
    </div>
  );
};

export default Navbar;
