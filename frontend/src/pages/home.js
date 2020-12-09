import React,{ useState, useEffect} from 'react';
import axios from 'axios';
import {Link} from "react-router-dom";
import './home.css'
 export default function Home(){
    return (
	<div className="container">
		<Link to="showArticles" className="link">
            <button id="showArticlesBtn" className="button">Show Articles</button><br/>
        </Link>
        <Link to="createArticle" className="link">
		    <button id="createArticleBtn" className="button">Create Article</button><br/>
        </Link>
	</div>
	)
}
 
