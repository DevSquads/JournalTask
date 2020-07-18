import Head from "next/head";
import {Navbar,Nav,NavDropDown} from "react-bootstrap";


const Header = ()=>{
    return (
    <div  style = {{marginBottom:"5%"}}>
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

    </div>
    )
    
}
export default Header;