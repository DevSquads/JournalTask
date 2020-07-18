import React, {useContext,useEffect} from "react";
import Header from "./layouts/Header";
import {Form,Button,Nav} from 'react-bootstrap'



let createArticle = ()=>{
    return(
        <>
    <Header/>
<div style = {{background:"#101010",height:"800px",width:"250px"}}>
    <Nav defaultActiveKey="/home" className="flex-column">
  <Nav.Link href="/home" style = {{color:"#FFFFFF"}}>Write an article</Nav.Link>
  <Nav.Link eventKey="link-1" style = {{color:"#FFFFFF"}}>View Article</Nav.Link>
  {/* <Nav.Link eventKey="link-2" style = {{color:"#FFFFFF"}}>Link</Nav.Link> */}
  {/* <Nav.Link eventKey="disabled" disabled> */}
    {/* Disabled
  </Nav.Link> */}
</Nav>
</div>

</>
    )

}


export default createArticle;