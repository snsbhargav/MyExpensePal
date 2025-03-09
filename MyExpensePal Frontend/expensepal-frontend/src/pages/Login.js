import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';
import logstyle from './Login.module.css'
import axios from 'axios';


const Login = ({ onLogin }) => {
    const [id, setId]=useState("");
    const [user, setUser] = useState(null);
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
            
            const {data}= await axios.post("http://localhost:8081/auth/login", formData);
            console.log("Login response data:", data);
            localStorage.setItem("token", data);
            onLogin(localStorage.getItem("token")); 
            setTimeout(() => navigate("/dashboard"), 100);
            setId(jwtDecode(data).sub);
            
            
        } catch (error) {
            console.error("Login Error:", error.response?.data || error.message);
        }
    };
    useEffect(() => {
        // console.log(id); 
        const fetchUserData = async () => {
            if (id) {
                try {
                    const token = localStorage.getItem("token");
                    const response = await axios.get(`http://localhost:8081/auth/getUserByEmail/${id}`, {
                        headers:{
                            Authorization: `Bearer ${token}`
                        }
                    });
                    console.log(response.data);  
                    setUser("Login response details:",response.data);
                    console.log(response.data.userId);
                } catch (error) {
                    console.error("Error fetching user data:", error);
                }
            }
        };
        fetchUserData();
    }, [id]); 

    return (
        <div className={logstyle.content}>
            <h1 style={{ textAlign: "center" }}>User Authentication</h1>
            <form onSubmit={submitHandler}>
                <input type="email" name="email" value={formData.email} onChange={changeHandler} placeholder='Email' /><br />
                <input type="password" name="password" value={formData.password} onChange={changeHandler} placeholder='Password' /><br />
                <input type="submit" value="Login" />
            </form>
        </div>
    );
};

export default Login;
