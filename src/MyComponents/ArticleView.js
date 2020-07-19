import React from 'react';
import TextField from '@material-ui/core/TextField';
import './Login.css';
import AccountCircle from '@material-ui/icons/AccountCircle';
import InputAdornment from '@material-ui/core/InputAdornment';
import Button from '@material-ui/core/Button';

const Article = {
    title: "Ay 7aga",
    describtion: "7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer ",
    author: "Tamer Mohamed",
    Approved: true
}

function ArticleView(){
    return (
        <div className="ArticleViewRoot">
            <div className="ArticleTitle">{Article.title}</div>
            <div className="ArticleAuthor">{Article.author}</div>
            <div className="ArticleDescribtion">{Article.describtion}</div>
        </div>
    )
}

export default ArticleView;