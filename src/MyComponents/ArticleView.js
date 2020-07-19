import React from 'react';
import './ArticleView.css';

const Article = {
    title: "Ay 7aga",
    description: "7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer 7agat keteeeeeeeeer ",
    author: "Tamer Mohamed",
    Approved: true
}

function ArticleView(){
    return (
        <div className="ArticleViewRoot">
            <div className="ArticleTitle">{Article.title}</div>
            <div className="ArticleAuthor">{"By " + Article.author}</div>
            <div className="ArticleDescription">{Article.description}</div>
        </div>
    )
}

export default ArticleView;