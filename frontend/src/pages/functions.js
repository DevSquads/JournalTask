import React,{ useState, useEffect} from 'react';
import axios from 'axios';

export async function updateData(obj){
    const base_url="http://127.0.0.1:8081/createArticle"
    try{
        const response = await axios.post(base_url, obj)
        console.log('update data has been called')
        return response;
    }
    catch(error){
        console.log(error)
    }
    // axios.post(base_url, obj)
    // .then(response =>{
    //     console.log('ay hagaaaa')
    //     if(response.status == 200){
    //         // setValueFlag('block')
    //         console.log('status: 200')
    //     }
    // })
    // .catch(error =>{
    //     console.log(error.response)
    // })
}
export async function deleteArticle(article){
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