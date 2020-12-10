import React,{ useState, useEffect} from 'react';
import axios from 'axios';

export async function updateDataApi(obj){
    const base_url="http://127.0.0.1:8081/createArticle"
    try{
        const response = await axios.post(base_url, obj)
        console.log('update data has been called')
        return response;
    }
    catch(error){
        console.log(error)
    }
}
export async function fetchDataApi(){
    const base_url="http://127.0.0.1:8081/showArticles"
    try{
        const response = await axios.get(base_url)
        return response        
    }
    catch(error){
        console.log(error.response)
    }
}
export async function deleteArticleApi(article){
    const base_url="http://127.0.0.1:8081/deleteArticle"
    try{
        const response = await axios.post(base_url, article)  
        console.log(response.data.Database)
        return response
    }
    catch(error){
        console.log(error.response)
    }
}