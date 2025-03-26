import axios from 'axios';
import React, { useEffect, useState } from 'react';
import '../styles/GetUserDetails.css';
import { useNavigate } from 'react-router-dom';

const GetUserDetails = () => {
  const [user, setUser] = useState({
    firstName:'',
    lastName:'',
    email:'',
    dateOfBirth:'',
    userId:'',
    bio:'',
    phone:'',
    gender:''
  })
  const navigate=useNavigate();

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const userId = localStorage.getItem("userId");
        const token = localStorage.getItem("token");
        const response = await axios.get(`http://localhost:8080/auth/getUser`, {
          headers: {
            Authorization: `Bearer ${token}`,
            'UserId': userId
          }
        });
        setUser(response.data);
        // console.log(response.data);
       
      } catch (error) {
        console.error("Error fetching User Details:", error);
      }
    };
    fetchUserDetails();
    // console.log(user);
  }, []);
   
  const handleEdit=(user)=>{
    console.log('Sending data:', user);
    navigate('/updateuserdetails', { state: { user } });
    // if (user && user.userId) {
    //   navigate('/updateuserdetails', { state: { user } });
    // } else {
    //   console.error("User details are not available yet");
    // }
  }

  return (
    <div className="udcontainer">
        <div className='image-edit'>
            <div>Image</div>
            <div><button className='edituser-btn' onClick={() => handleEdit(user)}>Edit</button></div>

        </div>
        <div className='userdetails'>
            <div className='usergroup'>
                <div className='usergroup-section'>
                    <label htmlFor='firstName'>First Name</label>
                    <input type='text' id='firstName' name='firstName' value={user.firstName} readOnly />
                </div>
                <div className='usergroup-section'>
                    <label htmlFor='lastName'>Last Name</label>
                    <input type='text' id='lastName' name='lastName' value={user.lastName} readOnly/>
                </div>
            </div>
            <div className='usergroup'>
                <div className='usergroup-section'>
                    <label htmlFor='email'>Email</label>
                    <input type='email' id='email' name='email' value={user.email} readOnly/>
                </div>
                <div className='usergroup-section'>
                    <label htmlFor='gender'>Gender</label>
                    <input type='text' id='gender' name='gender' value={user.gender} readOnly/>
                </div>
            </div>
           
            <div className='usergroup'>
                <div className='usergroup-section'>
                <label>Password</label>
                <input/>
                </div>
                <div className='usergroup-section'>
                    <label htmlFor='phone'>Phone</label>
                    <input type='phone' id='phone' name='phone' value={user.phone} readOnly/>
                </div>
                
            </div>
            <div className='usergroup'>
            <div className='usergroup-section'>
                <label htmlFor='userId'>UserId</label>
                <input type='UUID' id='userId' name='userId' value={user.userId} readOnly/>
                </div>
                <div className='usergroup-section'>
                <label htmlFor='dateOfBirth'>Date of Birth</label>
                <input type='date' id='dateOfBirth' name='dateOfBirth' value={user.dateOfBirth} readOnly/>
                
                </div>
            </div>
            <div className='usergroup'>
            <div className='usergroup-section'>
                <label>Bio</label>
                {/* <input className='bio-input'/> */}
                <textarea className="bio-input" value={user.bio} readOnly></textarea>
                </div>

            </div>

        </div>

      
    </div>
  );
};

export default GetUserDetails;
