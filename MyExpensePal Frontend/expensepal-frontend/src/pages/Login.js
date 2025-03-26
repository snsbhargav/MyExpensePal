import React, {useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';
// import logstyle from '../styles/Login.module.css'
import '../styles/Login.css';
import axios from 'axios';


const Login = ({ onLogin }) => {
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });

    const navigate = useNavigate();
    
    const changeHandler = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const submitHandler = async (e) => {
        e.preventDefault();
        
        try {
            
            const response = await axios.post("http://localhost:8080/auth/login", formData);
            console.log("Login response data:", response.data);
            localStorage.setItem("token", response.data);
            const token = localStorage.getItem("token");
            console.log("Setted Token is:", token);
            onLogin(localStorage.getItem("token")); 
            setTimeout(() => navigate("/dashboard"), 100);
            //const token = localStorage.getItem("token")
            localStorage.setItem("userId", (jwtDecode(token).sub));           
        } catch (error) {
            console.error("Login Error:", error.response?.data || error.message);
        }
    };

    return (
        <div className='login-main'>
            <div className="login-container">
                <form className="form-container" onSubmit={submitHandler}>
                    <h2>Login</h2>
                    <label htmlFor="email">Email</label>
                    <input type="email" name="email" value={formData.email} onChange={changeHandler} placeholder='Email' />
                    
                    <label htmlFor="password">Password</label>
                    <input type="password" name="password" value={formData.password} onChange={changeHandler} placeholder='Password' />
                    
                    <button type="submit" value="Login">Login</button>
                    
                    <p><a href="#">Forgot password?</a></p>
                    <p>Don't have an account? <a href="#">Sign up</a></p>            
                </form>
            </div>

        </div>
        
    );
};

export default Login;
