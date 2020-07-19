import React, { useContext, useEffect, useState } from "react";
//import Header from "./layouts/Header";
import {
  Form,
  Button,
  Nav,
  Row,
  Container,
  Col,
  Modal,
  Navbar,
  Card,
} from "react-bootstrap";
import articleInstance from "../Model/Article";
// import Head from "next/head";
// import author from "../Model/Author";
import fb from "../firebase";
import author from "../Model/Author";

let createArticle = () => {
  let [userid, setUserId] = useState("");
  let [username, setUserName] = useState("");
  let [popularid, setpopularId] = useState(0);
  const [approvedarticles, setapprovedArticles] = useState([]);
  const [filteredarticles, setfilteredArticles] = useState([]);
  let [filtered, setFiltered] = useState(false);

  useEffect(() => {
    let tempfn = async () => {
      let temparrTwo = await articleInstance.getapprovedArticles();
      setapprovedArticles(temparrTwo);
      let count = [];
      let max = 0;
      temparrTwo.forEach((item) => {
        var num = item.author;
        count[num] = count[num] ? count[num] + 1 : 1;
        if (count[num] > max) {
          max = count[num];
          setpopularId(num);
        }
      });
      
    };
    tempfn();

    // ApplyFilter(lowerBound,upperBound);
  }, []),
    useEffect(() => {
      console.log("HELLO ");
      let firebase = new fb();
      firebase.auth.onAuthStateChanged(async (user) => {
        console.log(user);
        if (user) {
          setUserId(user.uid);
          setUserName(user.displayName);
          console.log(user.displayName);
          console.log(user.uid);
        } else console.log("No user is signed in");
      });
    }, []);

  let filterArticlesBypopularity = (id) => {
    const filtered = approvedarticles.filter((val) => {
      return val.author == id;
    });
    const notFiltered = approvedarticles.filter((val) => {
      return val.author != id;
    });
    console.log(filtered);
    let sortedArray = [...filtered,...notFiltered];
    return sortedArray;
  };

  let getMyArticles = (myId)=>{
    let myArticles = approvedarticles.filter((val)=>{
      return val.author==myId;
    })
    
    console.log(myArticles);
    const notmyArticles = approvedarticles.filter((val)=>{
      return val.author!=myId;
    })
    // myArticles.push(approvedarticles.filter((val)=>{
    //   return val.author!=myId;
    // }))
    // return myArticles;
    let newArray = [...myArticles,...notmyArticles];
    return newArray;
    

  }
  let submit = async () => {
    console.log("Before upload");
    await articleInstance.createArticle(title, content, userid, username);
    setLgShow(true);
    console.log("After upload");
  };

  let [lgShow, setLgShow] = useState(false);
  let [viewArticle, setViewArticle] = useState(true);
  let [writeArticle, setwriteArticle] = useState(false);
  const [title, setTitle] = useState("");
  //const [description,setDescription] = useState("");
  const [content, setContent] = useState("");

  return (
    <>
      <Navbar bg="dark" variant="dark" style={{ marginBottom: "2rem" }}>
        <Navbar.Brand href="#home">Navbar</Navbar.Brand>
        <Nav className="mr-auto">
          <Nav.Link href="/">Register</Nav.Link>
          <Nav.Link href="/Login">Login</Nav.Link>
          <Nav.Link onClick = {()=>author.logout()}>Logout</Nav.Link>
        </Nav>
      </Navbar>

      <Modal
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
        <Modal.Body>An admin would approve your article. When approved they'll appear in the "view articles section</Modal.Body>
      </Modal>
      <Container>
        <Row>
          <Col sm={4}>
            <div style={{ background: "#101010", height: "800px" }}>
              <Nav defaultActiveKey="/home" className="flex-column">
                <Nav.Link
                  onClick={() => {
                    setwriteArticle(true), setViewArticle(false);
                    setFiltered(false);
                  }}
                  style={{ color: "#FFFFFF" }}
                >
                  Write an article
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    setViewArticle(true),
                      setwriteArticle(false),
                      setFiltered(false);
                      setapprovedArticles(getMyArticles(userid))
                  }}
                  eventKey="link-1"
                  style={{ color: "#FFFFFF" }}
                >
                  View Articles
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    setViewArticle(false),
                      setwriteArticle(false),
                      setFiltered(true),
                      // setfilteredArticles(
                      //   filterArticlesBypopularity(popularid)
                      // );
                      setfilteredArticles(filterArticlesBypopularity(popularid))
                  }}
                  eventKey="link-1"
                  style={{ color: "#FFFFFF" }}
                >
                  Most popular authors
                </Nav.Link>
              </Nav>
            </div>
          </Col>

          <Col sm={8}>
            {viewArticle &&
              approvedarticles.map((data) => (
                <Card style={{ width: "18rem", marginTop: "1.5rem" }}>
                  <Card.Body>
                    <Card.Title>{data.articleTitle}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">
                      Written by: {data.authorName}
                    </Card.Subtitle>
                    <Card.Text>{data.articleDescription}</Card.Text>
                    {/* <Card.Link href="#">Approve Article</Card.Link>
        <Card.Link href="#">Delete Article</Card.Link> */}
                  </Card.Body>
                </Card>
              ))}

            {filtered &&
              filteredarticles.map((data) => (
                <Card style={{ width: "18rem", marginTop: "1.5rem" }}>
                  <Card.Body>
                    <Card.Title>{data.articleTitle}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">
                      Written by: {data.authorName}
                    </Card.Subtitle>
                    <Card.Text>{data.articleDescription}</Card.Text>
                    {/* <Card.Link href="#">Approve Article</Card.Link>
      <Card.Link href="#">Delete Article</Card.Link> */}
                  </Card.Body>
                </Card>
              ))}

            {writeArticle && (
              <>
                <Form>
                  <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>Title</Form.Label>
                    <Form.Control
                      as="textarea"
                      rows="2"
                      onChange={(e) => {
                        setTitle(e.target.value), console.log(title);
                      }}
                    />
                  </Form.Group>
                  <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>Write your article</Form.Label>
                    <Form.Control
                      as="textarea"
                      rows="12"
                      onChange={(e) => {
                        setContent(e.target.value), console.log(title);
                      }}
                    />
                  </Form.Group>
                </Form>
                <Button
                  variant="primary"
                  type="submit"
                  style={{ marginTop: "5px" }}
                  onClick={() => {
                    submit();
                  }}
                >
                  Publish
                </Button>
              </>
            )}
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default createArticle;
