import firebase from "../firebase/firebase";
import * as frebase from "firebase";


let articleRef;
class Article {

    static articleInstance;
    authorName;
    title;
    description;
    content;

    constructor(){
        // if (process.browser) {
            let frbase = new firebase();
            articleRef = frbase.app.firestore().collection("articles");
        //   }
          if (!Article.articleInstance) {
            Article.articleInstance = this;
            return Article.articleInstance;
          } else {
            return Article.articleInstance;
          }
    }


    async createArticle(title,description){
        console.log("I am called");
        console.log("Article ",articleRef);

    
        articleRef.doc().set({
            title:title,
            description:description,
            // authorName:this.authorName,
            // content:this.content,
            approved:false

        }).then(doc=>(
            console.log("Article written ")
        ))
        console.log("HI");
        
    }

    async approveArticle(id){
        articleRef.doc(id).update({
            approved:true
        })
    }

    async deleteArticle(id){
        articleRef.doc(id).delete();
    }


}

let article = new Article();
export default article;