import React,{ useState, useEffect} from 'react';
import axios from 'axios';
import './showArticles.css'

 export default function ShowArticles(){
    const [articles,setValueArticles]=useState([]);
    const [authorName,setValueAuthorName]=useState('');
    const [flagAuthor, setValueFlagAuthor]=useState(false);
    const [flagReader, setValueFlagReader]=useState(false);
    const [authorContent, setValueAuthorContent]=useState('none');
    const [readerContent, setValueReaderContent]=useState('none');

    useEffect(()=>{
            const base_url="http://127.0.0.1:8081/showArticles"
            axios.get(base_url)
            .then(response =>{
                setValueArticles(response.data.Database)
            })
            .catch(error =>{
                console.log(error.response)
            })
    },[]);
    function showReaderArticles(){
        setValueFlagReader(prevFlag => !prevFlag );
        if(flagAuthor==true){
            setValueReaderContent("block");
        }
        else{
            setValueReaderContent("none");
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
    }
    function showAuthorArticles(){
        setValueFlagAuthor(prevFlag => !prevFlag );
        if(flagAuthor==true){
            setValueAuthorContent("block");
        }
        else{
            setValueAuthorContent("none");
        }
        var authorArticles=[]
        var otherArticles=[]
        for(var j=0; j<articles.length; j++){
            if(articles[j].authorName.toLowerCase()==authorName.toLowerCase()){
                authorArticles.push(articles[j])
                continue
            }
            otherArticles.push(articles[j])
        }
        authorArticles = authorArticles.concat(otherArticles);
        setValueArticles(authorArticles)
    }
    function deleteArticle(article){
        const base_url="http://127.0.0.1:8081/deleteArticle"
        axios.post(base_url,article)
        .then(response =>{
            console.log(response)
            setValueArticles(response.data.Database)
        })
        .catch(error =>{
            console.log(error.response)
        })
    }
    return (
	<div className="container">
        <div className="contents" style={{display:"flex"}}>
            <button id='author'className="userTypeButton" onClick={showAuthorArticles}>Author</button><br/>
            <button id = 'reader' className="userTypeButton" onClick={showReaderArticles}>Reader</button><br/>
            <input id='authorName'style={{display:authorContent}} onChange={handleInput} placeholder="Author Name"/><br/>
            <div>
                {articles.map(article=>
                    <div>
                        <div>
                            {article.title}<br/>
                            {article.authorName}<br/>
                            {article.description}<br/>
                        </div>
                        <button id='deleteBtn' onClick={()=>deleteArticle(article)}>Delete Article</button><br/>
                    </div>

                )}
            </div>
        </div>
    </div>
	)
}
 

