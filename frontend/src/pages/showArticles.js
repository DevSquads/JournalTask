import React,{ useState, useEffect} from 'react';
import axios from 'axios';


 export default function ShowArticles(){
      const [articles,setValueArticles]=useState([]);
      const [authorName,setValueAuthorName]=useState('');

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
    // function deleteArticle(){
    //     const base_url="http://127.0.0.1:8080/deleteArticle"
    //     axios.delete(base_url)
    //     .then(response =>{
    //         console.log(response)
    //     })
    //     .catch(error =>{
    //         console.log(error.response)
    //     })
    // }
    return (
	<div>
        <button onClick={showAuthorArticles}>Author</button><br/>
        <input onChange={handleInput} placeholder="Author Name"/><br/>
        <button onClick={showReaderArticles}>Reader</button><br/>
        <div>
            {articles.map(article=>
                <div>
                    {article.title}<br/>
                    {article.authorName}<br/>
                    {article.description}<br/>
                    {/* <button onClick={deleteArticle}>Delete Article</button><br/> */}
                </div>
            )}
        </div>
	</div>
	)
}
 

