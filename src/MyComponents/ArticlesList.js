import React from 'react';
import './ArticleView.css';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import $ from "jquery";

class ArticlesList extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            Articles: [],
        };
    }

    componentDidMount(){
        this.getAllArticles();
    }

    getAllArticles(){
        $.ajax("http://localhost:5000/articles/").done((response)=>{
            this.setState({Articles: response});
        })
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
                        </TableRow>
                    </TableHead>
                    <TableBody>
                    {Articles.map((article) => (
                        <TableRow key={article.__id}>
                            <TableCell>{article.authorName}</TableCell>
                            <TableCell>{article.title}</TableCell>
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
            </div>
        )
    }
}
export default ArticlesList;