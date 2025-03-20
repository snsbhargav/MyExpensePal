import React, { useEffect, useState } from "react";
import Navbar from "./pages/Navbar";
import LogoutButton from "./pages/LogoutButton";
import { BrowserRouter as Router, Routes, Route, Navigate, Link } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
import "./App.css";
import AddExpense from "./pages/AddExpense";
import GetExpense from "./pages/GetExpense";
import UpdateExpense from "./pages/UpdateExpense";
import GetUserDetails from "./pages/GetUserDetails";
import Home from "./pages/Home";
import Chat from "./pages/Chat";
import SignUp from "./pages/SignUp";
import AboutUs from "./pages/AboutUs";
import Settings from "./pages/Settings";
import Statistics from "./pages/Statistics";
  

const App = () => {
  const [token, setToken] = useState(localStorage.getItem("token") || null);

  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken && !token) {
      setToken(storedToken);
    }
  }, [token]); 

  const handleLogin = (newToken) => {
    localStorage.setItem("token", newToken);
    setToken(newToken);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    setToken(null);
  };

  return (
    <Router>
       <Routes>
        {/* Landing page which shows after logout or begining */}
        <Route path="/" element={<Home />} />

        {/* Authenticaton pages */}
        <Route path="/login" element={token ? <Navigate to="/dashboard" /> : <Login onLogin={handleLogin} />} />
        <Route path="/signup" element={<SignUp />} />
        {/* dashboard layout from here only for authenticated users */}
        {token ? (
          <Route path="/*"
          element={
            <div className="sec">
                <div className="sec1">
                  <Navbar />
                </div>
                <div className="sec2">
                  <div className="sec21">
                    <div className="profile">
                      <Link to="/getuserdetails">
                        <img src="/images/myphoto.jpg" alt="User" />
                      </Link>
                      <h3 style={{ padding: "5px" }}>Hi there,</h3>
                    </div>
                    <LogoutButton onLogout={handleLogout} />
                  </div>
                  <div className="sec22">
                    <Routes>
                      <Route path="/dashboard" element={<Dashboard />} />
                      <Route path="/getexpense" element={<GetExpense />} />
                      <Route path="/addexpense" element={<AddExpense />} />
                      <Route path="/updateexpense" element={<UpdateExpense />} />
                      <Route path="/getuserdetails" element ={<GetUserDetails />} />
                      <Route path="/chat" element={<Chat />} />
                      <Route path="/aboutus" element={<AboutUs />} />
                      <Route path="/settings" element={<Settings />} />
                      <Route path="/statistics" element={<Statistics />} />
                      <Route path="*" element={<Navigate to="/dashboard" />} />
                    </Routes>
                  </div>
                </div>
              </div>
            }
          />
        ) : (
          // Redirect unauthenticated users to landing page
          <Route path="*" element={<Navigate to="/" />} />
        )}
       </Routes>
      
    </Router>
  );
};

export default App;
