import React, {useContext,useEffect,useState} from "react";
import Header from "./layouts/Header";
import {Form,Button} from 'react-bootstrap'
import Head from "next/head";
import author from "../Model/Author";




let register = ()=>{

  let [email,setEmail] = useState("");
  let [pass,setPass] = useState("");
  let [username,setUSername] = useState("");


   return (
     <>
 
  {/* <Header/> */}
  <div style = {{textAlign:"center"}}>
  <h1 style = {{color:"#000000",margin:"2rem"}}><strong>Register</strong></h1>
  <Form>
    
  <Form.Group controlId="formBasicusername" style = {{width:350,marginLeft:580}}>
    <Form.Label>Author Name</Form.Label>
    <Form.Control type="text" placeholder="Author name" onChange = {(e)=>setUSername(e.target.value)} />
  </Form.Group>
  
  <Form.Group controlId="formBasicEmail" style = {{width:350,marginLeft:580}}>
    <Form.Label>Email address</Form.Label>
    <Form.Control type="email" placeholder="Enter email" onChange = {(e)=>setEmail(e.target.value)}  />
    <Form.Text className="text-muted">
      We'll never share your email with anyone else.
    </Form.Text>
  </Form.Group>

  <Form.Group controlId="formBasicPassword" style = {{width:350,marginLeft:580}}>
    <Form.Label>Password</Form.Label>
    <Form.Control type="password" placeholder="Password" onChange = {(e)=>setPass(e.target.value)} />
  </Form.Group>
  <span><a href = "/Login">Already have an account</a></span><br/>
 
</Form>
<Button variant="primary" type="submit" style = {{marginTop:"5px"}} onClick = {()=>{author.register(email,pass,username)}}>
    Submit
  </Button>
</div>
  </>


)




}


export default register;