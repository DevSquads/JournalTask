import React, {useContext,useEffect,useState} from "react";
//import Header from "./layouts/Header";
import {Form,Button,Nav,Row,Container,Col,Modal,Navbar} from 'react-bootstrap'
import articleInstance from "../Model/Article";
import Head from "next/head";
import author from "../Model/Author";
import fb from "../firebase/firebase";
//import {Navbar,Nav,NavDropDown} from "react-bootstrap";
//import article from "../Model/Article";



let createArticle = ()=>{

let [userid,setUserId] = useState("");
let [username,setUserName] = useState("");

  useEffect(()=>{

    console.log("HELLO ");
    let firebase = new fb();
    firebase.auth.onAuthStateChanged(async(user)=> {
      console.log(user);
      if(user){
        setUserId(user.uid);
        setUserName(user.displayName);
        console.log(user.displayName);
        console.log(user.uid);
      }
      else console.log("No user is signed in");
    });
  

  
},[])


  let submit = async()=>{
    console.log("Before upload");
   await articleInstance.createArticle(title,content,userid,username);
    console.log("After upload");
  }

    const [lgShow, setLgShow] = useState(false);
    const [title,setTitle] = useState("");
    //const [description,setDescription] = useState("");
    const [content,setContent] = useState("");
    return(
        <>
      {/* <div  style = {{marginBottom:"5%"}}>
        <Head>
        <title>Journal</title>
        <Navbar bg="dark" variant="dark">
    <Navbar.Brand href="#home">Navbar</Navbar.Brand>
    <Nav className="mr-auto">
      <Nav.Link href="#home">Home</Nav.Link>
      <Nav.Link href="#features">Features</Nav.Link>
      <Nav.Link href="#pricing">Pricing</Nav.Link>
    </Nav>
    </Navbar>
        </Head>

    </div> */}


    {/* <Modal
        size="lg"
        show={lgShow}
        onHide={() => setLgShow(false)}
        aria-labelledby="example-modal-sizes-title-lg"
      >
        <Modal.Header closeButton>
          <Modal.Title id="example-modal-sizes-title-lg">
            Success
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>An admin would approve your article , you'll be notified</Modal.Body>
      </Modal> */}
<Container>
    <Row>
        <Col sm={4}>
            <div style = {{background:"#101010", height:"800px"}}>
    <Nav defaultActiveKey="/home" className="flex-column">
  <Nav.Link href="/home" style = {{color:"#FFFFFF"}}>Write an article</Nav.Link>
  <Nav.Link eventKey="link-1" style = {{color:"#FFFFFF"}}>View Article</Nav.Link>
  {/* <Nav.Link eventKey="link-2" style = {{color:"#FFFFFF"}}>Link</Nav.Link> */}
  {/* <Nav.Link eventKey="disabled" disabled> */}
    {/* Disabled
  </Nav.Link> */}
</Nav>
</div>
</Col>

<Col sm = {8}>


    <Form>
    <Form.Group controlId="exampleForm.ControlTextarea1">
    <Form.Label>Title</Form.Label>
    <Form.Control as="textarea" rows="2" onChange = {e=>{setTitle(e.target.value),console.log(title)}} />
  </Form.Group>
    <Form.Group controlId="exampleForm.ControlTextarea1">
    <Form.Label>Write your article</Form.Label>
    <Form.Control as="textarea" rows="12" onChange = {e=>{setContent(e.target.value),console.log(title)}} />
  </Form.Group>

  
    </Form>
    <Button variant="primary" type="submit" style = {{marginTop:"5px"}} onClick = {()=>{submit()}}>
     Publish
   </Button>

</Col>
</Row>
</Container>

</>
    )

}


export default createArticle;