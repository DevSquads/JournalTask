
import React,{ useState, useEffect, } from 'react';
import axios from 'axios';
import './createArticle.css'
import {updateData} from './functions'
// var Button = require('react-button')

 export default function CreateArticle(){
    const [title,setValueTitle]=useState([]);
    const [description,setValueDescription]=useState([]);
    const [author,setValueAuthor]=useState([]);
    const [flag,setValueFlag]=useState('none');

	function handleTitleInput(event) {
		setValueTitle(event.target.value);
 	}
	function handleDescriptionInput(event) {
		setValueDescription(event.target.value);
 	}
	function handleAuthorInput(event) {
		setValueAuthor(event.target.value);
 	}
      
    async function createArticle() {
        console.log('Create Article has been called')
        const obj = {   
                      title:title,
                      description:description,
                      authorName:author
                    }
        const response = await updateData(obj)
        if(response.status==200){
            setValueFlag('block')
        }
    }
  
    return (
	<div className="container">
        <div className="content">
            <input id="title" className="creatArticleInput" onChange={handleTitleInput} placeholder="Title"/><br/>
            <input id="description" className="creatArticleInput"  onChange={handleDescriptionInput} placeholder="Description"/><br/>
            <input id="author" className="creatArticleInput"  onChange={handleAuthorInput} placeholder="Author"/><br/>
            <button id="createBtn" className="createArticleButton" onClick={createArticle}>Create Article</button><br/>
            
            <h3 style={{display:flag, color:'red'}}>Done</h3>
        </div>
	</div>
	)
}