import React, {useContext,useEffect, useState} from "react";
import Header from "./layouts/Header";
import {Nav,Button,Container,Row,Col,Navbar,Card} from 'react-bootstrap'
import article from "../Model/Article";
import userrole from "../Model/UserRole";
import fb from "../firebase/firebase";




let adminPanel = ()=>{


  const [unapprovedarticles,setunapprovedArticles] = useState([]);
  const [approvedarticles,setapprovedArticles] = useState([]);

  let [userid, setUserId] = useState("");
  let [username, setUserName] = useState("");

  useEffect(() => {
    let tempfn = async () => {
      let temparr = await article.getUnapprovedArticles();
      let temparrTwo = await article.getapprovedArticles(); 
      console.log("Unapproved Articles ", temparr);
      setunapprovedArticles(temparr);
      setapprovedArticles(temparrTwo);
     
    };
    tempfn();

    // ApplyFilter(lowerBound,upperBound);
  }, []),

  useEffect(() => {
    let tempfn = async () => {
      console.log("HELLO ");
      let firebase = new fb();
      firebase.auth.onAuthStateChanged(async (user) => {
        console.log(user);
        if (user) {
          setUserId(user.uid);
          setUserName(user.displayName);
          console.log(user.displayName);
          let role = await userrole.getUserRole(user.uid);
          if(role !="admin")
          location.href = "./createArticle";
          
        } else console.log("No user is signed in");
      });
     
      
      
     
    };
    tempfn();

    // ApplyFilter(lowerBound,upperBound);
  }, [])



    return (

      <>
       <Navbar bg="dark" variant="dark" style = {{marginBottom:"2rem"}}>
    <Navbar.Brand href="#home">Navbar</Navbar.Brand>
    <Nav className="mr-auto">
      <Nav.Link href="#home">Home</Nav.Link>
      <Nav.Link href="#features">Features</Nav.Link>
      <Nav.Link href="#pricing">Pricing</Nav.Link>
    </Nav>
    </Navbar>
        <Container>
    <Row>
        <Col sm={4}>
            <div style = {{background:"#101010", height:"800px"}}>
    <Nav defaultActiveKey="/home" className="flex-column">
  <Nav.Link href="/home" style = {{color:"#FFFFFF"}}>View Unapproved Articles</Nav.Link>
  <Nav.Link eventKey="link-1" style = {{color:"#FFFFFF"}}>View Approved Article</Nav.Link>
 
</Nav>
</div>
</Col>

<Col sm = {8}>
  <h2><strong>Unapproved Articles</strong></h2>
  {unapprovedarticles.length==0 &&(
    <p><strong>There are currently no un approved articles</strong></p>
  )}
{unapprovedarticles.map((data,index)=>(
  <Card style={{ width: '18rem',marginTop:"1.5rem"}}>
  <Card.Body>
    <Card.Title>{data.articleTitle}</Card.Title>
    <Card.Subtitle className="mb-2 text-muted">Written by: {data.authorName}</Card.Subtitle>
    <Card.Text>
      {data.articleDescription}
    </Card.Text>
    {/* <Card.Link href="#">Approve Article</Card.Link>
    <Card.Link href="#">Delete Article</Card.Link> */}
    <Button onClick = {()=>article.approveArticle(data.articleId)}>Approve Article</Button>
    <br/>
    <Button style = {{marginTop:"2rem"}} onClick = {()=>article.deleteArticle(data.articleId)}>Delete Article</Button>

    

  </Card.Body>
</Card>

))

}
<h2><strong>Approved Articles</strong></h2>
{approvedarticles.map((data,index)=>(
  <Card style={{ width: '18rem',marginTop:"1.5rem"}}>
  <Card.Body>
    <Card.Title>{data.articleTitle}</Card.Title>
    <Card.Subtitle className="mb-2 text-muted">Written by: {data.authorName}</Card.Subtitle>
    <Card.Text>
      {data.articleDescription}
    </Card.Text>
    {/* <Card.Link href="#">Approve Article</Card.Link>
    <Card.Link href="#">Delete Article</Card.Link> */}
    {/* <Button onClick = {()=>article.approveArticle(data.articleId)}>Approve Article</Button>
    <br/> */}
    <Button style = {{marginTop:"2rem"}} onClick = {()=>article.deleteArticle(data.articleId)}>Delete Article</Button>

    

  </Card.Body>
</Card>

))

}
    
 

</Col>
</Row>
</Container>
</>

    )

    

}


export default adminPanel;