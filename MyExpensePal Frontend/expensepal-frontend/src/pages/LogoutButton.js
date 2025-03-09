// import React from "react";
// import { useNavigate } from "react-router-dom";
// import "../App.css";

// const LogoutButton = () => {
//   const navigate = useNavigate();

//   const handleLogout = () => {
    
    
//     navigate("/login")
//   };

//   return (
//     <div className="header">
//       {/* <div className="profile">
//             <img src="/images/myphoto.jpg" alt="User"/>
//             <h3 style={{padding:"5px"}}>Hi there,</h3>
//       </div> */}
//       <div className="logout"><button className="logout-btn" onClick={handleLogout}>Logout</button></div>
      
//     </div>
//   );
// };

// export default LogoutButton;
import React from 'react';
import { Link } from 'react-router-dom';

const LogoutButton = ({ onLogout }) => {
    return (
        <Link to="/logout"><button onClick={onLogout} style={{ margin: "10px", padding: "8px 15px", cursor: "pointer" }}>Logout
        </button></Link>
    );
};

export default LogoutButton;
