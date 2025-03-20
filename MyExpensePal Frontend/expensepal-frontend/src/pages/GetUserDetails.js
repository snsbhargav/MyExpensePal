import axios from 'axios';
import React, { useEffect, useState } from 'react'

const GetUserDetails = () => {
    const[user, setUser]=useState(null);
   // const[error, setError]=useState(null);
    useEffect(()=>{
        const fetchUserDetails= async ()=>{
            try{
                const userId = localStorage.getItem("userId");
                //console.log(userId)
                const token = localStorage.getItem("token");
                const response =  await axios.get(`http://localhost:8080/auth/getUser`,{
                    headers:{
                        Authorization: `Bearer ${token}`,
                        'UserId': userId
                    }
                });
                setUser(response.data);
    
            }catch (error) {
                console.error("Error fetching User Details:", error);
                //setError("Failed to fetch User Details.");
            }
        }     
        fetchUserDetails();   
    },[]); 

  return (
    <div>
        {user ? (
                <div>
                    <h3>User Details:</h3>
                    <p>Name: {user.firstName} {user.lastName}</p>
                    <p>Bio: {user.bio}</p>
                    <p>Email: {user.email}</p>
                    <p>Gender: {user.gender}</p>
                    <p>User Id: {user.userId}</p>
                    <p>Phone: {user.phone}</p>
                    <p>Date Of Birth: {user.dateOfBirth}</p>
                    
                </div>
            ) : (
                <p>Loading user details...</p>
            )}
      
    </div>
  )
}

export default GetUserDetails
