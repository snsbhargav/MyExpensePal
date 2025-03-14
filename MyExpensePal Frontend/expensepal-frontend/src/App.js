import React, { useEffect, useState } from "react";
import Navbar from "./pages/Navbar";
import LogoutButton from "./pages/LogoutButton";
import { BrowserRouter as Router, Routes, Route, Navigate, Link } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
// import SignUp from "./pages/SignUp";
import "./App.css";
import AddExpense from "./pages/AddExpense";
import Support from "./pages/Support";
import GetExpense from "./pages/GetExpense";
import UpdateExpense from "./pages/UpdateExpense";
import GetUserDetails from "./pages/GetUserDetails";
import ViewExpense from "./pages/ViewExpense";



const App = () => {
  const [token, setToken] = useState(localStorage.getItem("token") || null);

  // useEffect(()=>{
  //   const storedToken = localStorage.getItem("token");
  //   if(storedToken) setToken(storedToken);
  // }, [])
  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken && !token) {
      //!tken id used to prevent unnecessary updates like it will compared with storedToken if it is same or already there then it wont update
      setToken(storedToken);
    }
  }, [token]); 
  
  const handleLogin = (newToken) => {
    localStorage.setItem("token", newToken);
    setToken(newToken);
  };

  const handleLogout = () =>{
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    setToken(null);
  }
  return (
    
    <Router>
    <div className="sec">
      <div className="sec1">
        {token && <Navbar />} {/*navbar will display onli if user logged in*/}
      </div>
      <div className="sec2">
        <div className="sec21">
          { token &&(
            <div className="profile">
            <Link to="/signup"><img src="/images/myphoto.jpg" alt="User"/></Link>
            <h3 style={{padding:"5px"}}>Hi there,</h3>
          </div>
          )}
          {token && (
            <div>{token && <LogoutButton onLogout={handleLogout} />}</div>
          )}
          
        </div>
        <div className="sec22">
          <div>
            <Routes>
             <Route path="/dashboard" element={ token ? <Dashboard /> : <Navigate to="/login" />}/>
             <Route path="/login" element={<Login onLogin={handleLogin} />} />
             <Route path="/getexpense" element={ token ? <GetExpense /> : <Navigate to="/login" />}/>
             <Route path="/signup" element={<GetUserDetails />} />
             {/* <Route path="/signup" element={<UpdateExpense />} /> */}
             <Route path="/addexpense" element={ token ? <AddExpense /> : <Navigate to="/login" />} />
             <Route path="/updateexpense" element={ token ? <UpdateExpense /> : <Navigate to="/login" />} />
             <Route path="/viewexpense" element={ token ? <ViewExpense /> : <Navigate to="/login" />} />
             <Route path="/support" element={<Support />} />
             <Route path="*" element={<Navigate to={token ? "/" : "/login"} />} />
           </Routes>

           </div>
        </div>
      </div>
    </div>
    

    </Router>
  );
};

export default App;
