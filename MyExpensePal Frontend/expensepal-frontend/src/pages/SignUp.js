import React,{useState} from 'react';

function SignUp() {
  const [data,setData] = useState({
    username:'',
    password:'',
    confirmpassword:'',
    email:'',

  })
  const {username,password,email,confirmpassword} =data;
  const changeHandler=e=>{
    setData({...data,[e.target.name]:e.target.value})
  }
  const submitHandler = e =>{
    e.preventDefault();
    if(username.length<=4){
      alert("User Name must be greater than 4 characters")
    }else{
      console.log(data);
    }
  }
  return (
    <div className='content'>
      <center>
        <form onSubmit={submitHandler}>
          <h1>SignUp to create your accout</h1>
          <input type='text' name='username' value={username} onChange={changeHandler} placeholder='UserName' /><br />
          <input type='email' name='email' value={email} onChange={changeHandler} placeholder='Email' /><br />
          <input type='password' name='password' value={password} onChange={changeHandler} placeholder='Password' /><br />
          <input type='passsword' name='confirmpassword' value={confirmpassword} onChange={changeHandler} placeholder='Confirm Password' /><br />
          <input type='submit' name='submit' />
        </form>
      </center>
    </div>
  );
}

export default SignUp;
