import React, {useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';
import logstyle from '../styles/Login.module.css'
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
            
            const response = await axios.post("http://localhost:8081/auth/login", formData);
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
    // useEffect(() => {
    //     const fetchUserData = async () => {
    //         if (id) {
    //             try {
    //                 const token = localStorage.getItem("token");
    //                 console.log(id);
    //                 const headers = {
    //                     Authorization: `Bearer ${token}`
    //                 };
                    
    //                 console.log("Request Headers:", headers);
    //                 const response = await axios.get('http://localhost:8081/auth/getUserById/' + id, {
    //                     headers: headers,
    //                 });
    //                 console.log("Respone from API is: ",response);
    //                 console.log(response.data);  
    //                 setUser(response.data);
    //                // localStorage.setItem()
    //             } catch (error) {
    //                 console.error("Error fetching user data:", error);
    //             }
    //         }
    //     };
    //     fetchUserData();
        
    // }, [id]); 
    // useEffect(() => {
    //     if (user.userId) {
    //         console.log("Updated UUID:", user.userId);
    //         localStorage.setItem("user", JSON.stringify(user));//JSON.stringfy(user)=> converts objects to a json string
    //         //if i need to user or get user details parse keyword to be uses: "const storedUser = JSON.parse(localStorage.getItem("user"));"
    //     }
        
    // }, [user]);
    

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
