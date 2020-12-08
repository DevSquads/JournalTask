
import React,{ useState, useEffect} from 'react';
import axios from 'axios';

 export default function CreateArticle(){
    const [articles,setValueArticles]=useState([]);
    const [title,setValueTitle]=useState([]);
    const [description,setValueDescription]=useState([]);
    const [author,setValueAuthor]=useState([]);
	function handleTitleInput(event) {
		setValueTitle(event.target.value);
 	}
	function handleDescriptionInput(event) {
		setValueDescription(event.target.value);
 	}
	function handleAuthorInput(event) {
		setValueAuthor(event.target.value);
 	}

	function createArticle(){
        const obj={ title:title,
                    description:description,
                    authorName:author}
        const base_url="http://127.0.0.1:8081/createArticle"
        axios.post(base_url, obj)
        .then(response =>{
            console.log(response)
        })
        .catch(error =>{
            console.log(error.response)
        })
    }
    return (
	<div>
		<input onChange={handleTitleInput} placeholder="Title"/><br/>
		<input onChange={handleDescriptionInput} placeholder="Description"/><br/>
		<input onChange={handleAuthorInput} placeholder="Author"/><br/>
		<button onClick={createArticle}>Create Article</button><br/>
	</div>
	)
}