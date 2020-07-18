import React, {useContext,useEffect} from "react";
import Header from "./layouts/Header";
import {Form,Button} from 'react-bootstrap'


let Login = ()=>{



    return (
      <>
  
   <Header/>
   <div style = {{textAlign:"center"}}>
   <h1 style = {{color:"#000000",margin:"2rem"}}><strong>Login</strong></h1>
   <Form>
     
   
   
   <Form.Group controlId="formBasicEmail" style = {{width:350,marginLeft:580}}>
     <Form.Label>Email address</Form.Label>
     <Form.Control type="email" placeholder="Enter email" />
     <Form.Text className="text-muted">
       We'll never share your email with anyone else.
     </Form.Text>
   </Form.Group>
 
   <Form.Group controlId="formBasicPassword" style = {{width:350,marginLeft:580}}>
     <Form.Label>Password</Form.Label>
     <Form.Control type="password" placeholder="Password" />
   </Form.Group>
   <span><a href = "/">new member? Sign up for free</a></span><br/>
  
   
   <Button variant="primary" type="submit" style = {{marginTop:"5px"}}>
     Submit
   </Button>
 </Form>
 </div>
   </>
 
 
 )
 
 
 
 
 }
 
 
 export default Login;