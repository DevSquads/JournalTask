import React from 'react';
import './ArticleView.css';

function ArticleView(props){
    let {article} = props;
    return (
        <div className="ArticleViewRoot">
            <div className="ArticleTitle">{article.title}</div>
            <div className="ArticleAuthor">{"By " + article.authorName}</div>
            <div className="ArticleDescription">{article.description}</div>
        </div>
    )
}

export default ArticleView;