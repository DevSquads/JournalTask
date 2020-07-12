import React from 'react';
import { Card, CardImg, CardText, CardBody,CardTitle, CardSubtitle} from 'reactstrap';
import {Loading} from './LoadingComponent';

function RenderCard({item, isLoading, errMess}){
    if(isLoading){
        return(
            <Loading />
        );
    } else if (errMess){
        return(
            <h4>{errMess}</h4>
        );
    } else {
        return(
                <Card>
                    <CardBody>
                        <CardTitle>{item.title}</CardTitle>
                        {item.author ? <CardSubtitle>{item.author}</CardSubtitle> : null }
                        <CardText>{item.description}</CardText>
                    </CardBody>
                </Card>
        );
    }
}
function Home(props){
    return(
        <div className="container">
            <h1>Home Component</h1>
        </div>
    );
}
export default Home;