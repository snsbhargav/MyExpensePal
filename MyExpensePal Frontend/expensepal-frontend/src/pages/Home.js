import React from "react";
import { Link } from "react-router-dom";
import "../styles/Home.css";

const Home = () => {
  return (
    <div className="main-container">
      <h1>Welcome to Expense Tracker</h1>
      <p>Manage your expenses.</p>
      <button><Link to="/login">Sign In</Link></button>
      <button><Link to="/signup">Sign Up</Link></button>      
    </div>
  );
};

export default Home;
