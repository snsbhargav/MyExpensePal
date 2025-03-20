import axios from 'axios';
import React,{useState} from 'react';
import '../styles/SignUp.css';

function SignUp() {
  const [data,setData] = useState({
    firstName:'',
    lastName:'',
    email:'',
    password:'',
    gender:'',
    dateOfBirth:'',
    bio:'',
    phone:''
  })
  const {firstName, lastName,email, password, gender, dateOfBirth, bio, phone} =data;
  const changeHandler=e=>{
    setData({...data,[e.target.name]:e.target.value})
  }
  const submitHandler = e =>{
    e.preventDefault();
    axios.post("",data)
    .then(reponse => {
      alert("Registered Successfully Please Login")
      setData({
        firstName:"", lastName: "", email:"", password:"", gender:"", dateOfBirth:"", bio:"", phone:""});
    })
    .catch(error=>{
      console.error("Sign Up failed", error);
    })
  }
  return (
    <div className='content'>
      
        <form onSubmit={submitHandler} className='signup-form'>
          <h1>SignUp to create your accout</h1>

          <div className='form-name' style={{display:"flex"}}>
            <div style={{display:"flex", flexDirection:"column"}}>
              <label htmlFor='firstName'>First Name</label>
              <input type='text' id='firstName' name='firstName' value={firstName} onChange={changeHandler} required />
            </div>

            <div style={{display:"flex", flexDirection:"column"}}>
              <label htmlFor='LastName'>Last Name</label>
              <input type='text' id='lastName' name='lastName' value={lastName} onChange={changeHandler} required />
            </div>
          </div>

          <label htmlFor='email'>Email</label>
          <input type='email' id='email' name='email' value={email} onChange={changeHandler} required />

          <label htmlFor='password'>Password</label>
          <input type='password' id='password' name='password' value={password} onChange={changeHandler} required />

          <label htmlFor='gender'>Gender</label>
          <input type='text' id='gender' name='gender' value={gender} onChange={changeHandler} required />

          <label htmlFor='dateOfBirth'>Date of Birth</label>
          <input type='date' id='dateOfBirth' name='dateOfBirth' value={dateOfBirth} onChange={changeHandler} required />

          <label htmlFor='bio'>Bio</label>
          <input type='text' id='bio' name='bio' value={bio} onChange={changeHandler} required />

          <label htmlFor='phone'>Phone</label>
          <input type='phone' id='phone' name='phone' value={phone} onChange={changeHandler} required />

          <input type='submit' name='submit' />
        </form>
      
    </div>
  );
}

export default SignUp;
