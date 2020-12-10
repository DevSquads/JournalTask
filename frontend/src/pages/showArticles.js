import React,{ useState, useEffect} from 'react';
import axios from 'axios';
import './showArticles.css'
import {fetchDataApi} from './API'
import {deleteArticleApi} from './API'


 export default function ShowArticles(){
    const [articles,setValueArticles]=useState([]);
    const [authorName,setValueAuthorName]=useState('');
    const [flagAuthor, setValueFlagAuthor]=useState(false);
    const [flagReader, setValueFlagReader]=useState('block');
    const [authorNameInput, setValueAuthorNameInput]=useState('none');
    const [articlesList, setValueArticlesList]=useState('none');

    useEffect(()=>{
            fetchData()
            window.addEventListener('load', showAuthorInputField());
    },[]);
    async function fetchData(){
        const response = await fetchDataApi()
        setValueArticles(response.data.Database)
    }
    function showReaderArticles(){
        setValueFlagReader(prevFlag => !prevFlag );
        if(flagAuthor==true){
            setValueArticlesList("block");
        }
        else{
            setValueArticlesList("none");
        }
        var dict={};
        for(var i=0; i<articles.length; i++){
            if(!dict[articles[i].authorName]){
                dict[articles[i].authorName]=1;
                continue;
            }
            dict[articles[i].authorName]++;
        }
        var items = Object.keys(dict).map(function(key) {
            return [key, dict[key]];
        });
        items.sort(function(first, second){
            return second[1] - first[1];
        });
        var sortedArticles=[]
        for(var i=0; i<items.length; i++){
            for(var j=0; j<articles.length; j++){
                if(articles[j].authorName==items[i][0]){
                    sortedArticles.push(articles[j])
                }
            }
        }
        setValueArticles(sortedArticles)
    }
    function handleInput(event) {
        setValueAuthorName(event.target.value);
        setValueArticlesList('block')
        var authorArticles=[]
        var otherArticles=[]
        for(var j=0; j<articles.length; j++){
            if(articles[j].authorName.toLowerCase().includes(authorName.toLowerCase())){
                authorArticles.push(articles[j])
                continue
            }
            otherArticles.push(articles[j])
        }
        authorArticles = authorArticles.concat(otherArticles);
        setValueArticles(authorArticles)
    }
    function showAuthorInputField(){
        setValueFlagAuthor(true);
        if(flagAuthor==true){
            setValueAuthorNameInput("block");
            setValueFlagReader('None')
        }
        else{
            setValueAuthorNameInput("none");
        }
    }
    async function deleteArticle(article){
        const response = await deleteArticleApi(article)
        setValueArticles(response.data.Database)
        setValueArticlesList('none')
        setValueAuthorNameInput('none')
        setValueFlagReader('block')
        setValueAuthorName('')
    }
    return (
	<div className="containerShow">
        <div className="contents row" style={{display:"flex"}}>
            <button id='author'className="userTypeButton" onClick={showAuthorInputField}>Author</button><br/>
            <button id = 'reader' className="userTypeButton" style={{display:flagReader}} onClick={showReaderArticles}>Reader</button><br/>
            <input id='authorName' style={{display:authorNameInput}} value={authorName} onChange={handleInput} placeholder="Author Name"/><br/>
        </div>

        <div className="articles row" style={{display:articlesList}}>
            {articles.map(article=>
                <div>
                    <div>
                        <h2>{article.title}</h2>
                        <h5>By: {article.authorName}</h5>
                        <h5>{article.description}</h5>
                    </div>
                    <button id='deleteBtn'onClick={()=>deleteArticle(article)}>Delete</button><br/>
                </div>

            )}
        </div>
    </div>
	)
}
 

