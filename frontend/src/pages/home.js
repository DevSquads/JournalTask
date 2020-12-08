import React,{ useState, useEffect} from 'react';
import axios from 'axios';
import {Link} from "react-router-dom";

 export default function Home(){
    return (
	<div>
		<Link to="showArticles">
            <button>Show Articles</button><br/>
        </Link>
        <Link to="createArticle">
		    <button>Create Article</button><br/>
        </Link>
        <Link to="deleteArticle">
		    <button>Delete Article</button>
        </Link>
	</div>
	)
}
 



// import React,{ useState, useEffect} from 'react';
// import axios from 'axios';

// async function getArticles(){
//     try{
//         const response = await axios.get('database.json');
//         console.log(response);
//         return response;
//     }
//     catch(error){
//         console.log(error)
//     }
//   }

//  export default function Home(){
//       const [articles,setValueArticles]=useState([]);
//       const [title,setValueTitle]=useState([]);
//       const [description,setValueDescription]=useState([]);
//       const [author,setValueAuthor]=useState([]);
//       useEffect(()=>{
//             getArticles().then(response =>{
//                 setValueArticles(response.data.Database)
                
//             }); 
// 	 },[]);
// 	function handleTitleInput(event) {
// 		setValueTitle(event.target.value);
//  	}
// 	function handleDescriptionInput(event) {
// 		setValueDescription(event.target.value);
//  	}
// 	function handleAuthorInput(event) {
// 		setValueAuthor(event.target.value);
//  	}

// 	function createArticle(){
		
//     }
//     function showArticles(){
//         var dict={};
//         for(var i=0; i<articles.length; i++){
//             if(!dict[articles[i].authorName]){
//                 dict[articles[i].authorName]=1;
//                 continue;
//             }
//             dict[articles[i].authorName]++;
//         }
//         var items = Object.keys(dict).map(function(key) {
//             return [key, dict[key]];
//         });
//         items.sort(function(first, second){
//             return second[1] - first[1];
//         });
//         var sortedArticles=[]
//         for(var i=0; i<items.length; i++){
//             for(var j=0; j<articles.length; j++){
//                 if(articles[j].authorName==items[i][0]){
//                     sortedArticles.push(articles[j])
//                 }
//             }
//         }
//         setValueArticles(sortedArticles)
//     }

//     return (
// 	<div>
// 		<button onClick={showArticles}>Show Articles</button><br/>
//         <div>
//             {articles.map(article=>
//                 <div>
//                     {article.title}<br/>
//                     {article.authorName}<br/>
//                     {article.description}<br/>
//                 </div>
//             )}
//         </div>
// 		<input onChange={handleTitleInput} placeholder="Title"/><br/>
// 		<input onChange={handleDescriptionInput} placeholder="Description"/><br/>
// 		<input onChange={handleAuthorInput} placeholder="Author"/><br/>
// 		<button onClick={createArticle}>Create Article</button><br/>
// 		<input type="text" placeholder="Title"/><br/>
// 		<button>Delete Article</button>
// 	</div>
// 	)
// }
 


