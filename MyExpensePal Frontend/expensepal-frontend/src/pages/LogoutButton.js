import React from 'react';
import {useNavigate } from 'react-router-dom';
import '../App.css';

const LogoutButton = ({ onLogout }) => {
    const navigate = useNavigate();
    const handleLogout=()=>{
        onLogout();
        navigate("/login");
    }
    return (
        <button onClick={handleLogout} className='logout-btn'>Logout</button>
    );
};

export default LogoutButton;
