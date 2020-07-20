import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Axios from 'axios';
import './CreateArticle.css';

class CreateArticle extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            title: "",
            description: ""
        }
        this.handleTitleChange = this.handleTitleChange.bind(this);
        this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
        this.SubmitArticle = this.SubmitArticle.bind(this);
    }
    
    handleTitleChange(event) {
		this.setState({ title: event.target.value });
    }
    
    handleDescriptionChange(event){
		this.setState({ description: event.target.value });
    }

    SubmitArticle(){
        Axios.post("http://localhost:5000/articles/add",{
             authorName: this.props.user.userName, 
             authorID: this.props.user._id,
             description: this.state.description,
             title: this.state.title
            }).then((response) => {
            this.props.setComponent('Articles List');
        });
    }

    render(){
        return (
            <div className="CreateArticleRoot">
                <TextField
                    value={this.state.title}
                    onChange={this.handleTitleChange}
                    fullWidth={true}
                    label="Title"
                    variant="outlined"
                    placeholder="Enter article title"
                />
                <TextField
                    value={this.state.description}
                    onChange={this.handleDescriptionChange}
                    fullWidth={true}
                    className={"ArticleDescription"}
                    multiline
                    rows={10}
                    label="Article Description"
                    variant="outlined"
                    placeholder="Enter article description"
                />
                <Button onClick={() => this.SubmitArticle()}>Submit Article</Button>
            </div>
        )
    }
}

export default CreateArticle;