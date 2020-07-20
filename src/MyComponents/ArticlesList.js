import React from 'react';
import './ArticleView.css';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Axios from "axios";
import Button from '@material-ui/core/Button';
import './ArticlesList.css';

class ArticlesList extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            Articles: [],
        };
    }

    componentDidMount(){
        this.getArticles();
    }

    componentDidUpdate(prevProps){
        if(this.props.approve != prevProps.approve){
            this.setState({Articles:[]});
            this.getArticles();
        }
    }

    getArticles(){
        if(this.props.approve){
            Axios.post("http://localhost:5000/articles/", {approved: false}).then((response)=>{
                this.setState({Articles: response.data});
            })
        }
        else{
            Axios.post("http://localhost:5000/articles/", {approved: true}).then((response)=>{
                this.setState({Articles: response.data});
            })
        }
    }

    render(){
        let {Articles} = this.state;
        return (
            <div className="ArticlesListRoot">
                <Table >
                    <TableHead>
                        <TableRow>
                            <TableCell>Author</TableCell>
                            <TableCell>Title</TableCell>
                            <TableCell></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                    {Articles.map((article) => (
                        <TableRow key={article.__id}>
                            <TableCell>{article.authorName}</TableCell>
                            <TableCell>{article.title}</TableCell>
                            <TableCell>
                                <Button className="ViewButton">View</Button>
                                {this.props.approve ? <Button className="ApproveButton" disabled={this.props.user.userType != 1}>Approve</Button> : null}
                                <Button className="DeleteButton" disabled={this.props.user.userType != 1}>Delete</Button>
                            </TableCell>
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
            </div>
        )
    }
}
export default ArticlesList;