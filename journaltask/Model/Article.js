import firebase from "../firebase/firebase";
import * as frebase from "firebase";


let articleRef;
class Article {

    static articleInstance;
    authorName;
    title;
    description;
    content;
    unapprovedArticles;
    approvedArticles;

    constructor(){
        this.unapprovedArticles = [];
        this.approvedArticles = [];
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


    async createArticle(title,description,authorid,authorname){
        console.log("I am called");
        console.log("Article ",articleRef);

    
        articleRef.doc().set({
            authorid:authorid,
            title:title,
            description:description,
            authorName:authorname,
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

    async getUnapprovedArticles(){
      let temp =   await articleRef.where("approved","==",false).get().then((snapshot)=>{
          snapshot.docs.forEach((doc)=>{
              let tdoc = {
                  articleId:doc.id,
                  articleTitle: doc.data().title,
                  articleDescription:doc.data().description,
                  author:doc.data().authorid,
                  authorName:doc.data().authorName
              }
              this.unapprovedArticles.push(tdoc);
          })
      })
      return this.unapprovedArticles;
    }

    
    async getapprovedArticles(){
        let temp =   await articleRef.where("approved","==",true).get().then((snapshot)=>{
            snapshot.docs.forEach((doc)=>{
                let tdoc = {
                    articleId:doc.id,
                    articleTitle: doc.data().title,
                    articleDescription:doc.data().description,
                    author:doc.data().authorid,
                    authorName:doc.data().authorName
                }
                this.approvedArticles.push(tdoc);
            })
        })
        return this.approvedArticles;
      }
}

let article = new Article();
export default article;