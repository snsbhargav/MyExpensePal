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
          <li><Link to="/addexpense">Expense Management</Link></li>
          <li><Link to="/getexpense">My Expenses</Link></li>
          <li><Link to="/signup">Chat</Link></li>
          <li><Link to="/signup">Analysis</Link></li>
          <li><Link to="/login">Settings</Link></li>
          <li><Link to="/support">ContactUs</Link></li>
          
        </ul>  </div>      
    </div>
  );
};

export default Navbar;
// import React from "react";
// import { Link } from "react-router-dom";
// import "./App.css";

// const Navbar = () => {
//   return (
//     <div className="sidebar">
//       <h2>MyExpensePal</h2>
//       <div className="profile">
//         <img src="profile.jpg" alt="User" />
//         <p>Praveen Muddunur</p>
//       </div>
//       <ul>
//         <li><Link to="/">Dashboard</Link></li>
//         <li><Link to="/update-expense">Update Expense</Link></li>
//         <li><Link to="/delete-expense">Delete Expense</Link></li>
//         <li><Link to="/get-expense">Get Expense</Link></li>
//         <li><Link to="/chat">Chat</Link></li>
//         <li><Link to="/add-expense">Add Expense</Link></li>
//         <li><Link to="/support">Support</Link></li>
//       </ul>
//     </div>
//   );
// };

// export default Navbar;
