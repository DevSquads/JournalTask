import firebase from "../firebase/firebase";


let articleRef;
class Article {

    authorName;
    title;
    description;
    content;

    constructor(){
        if (process.browser) {
            let frbase = new firebase();
            articleRef = frbase.app.firestore().collection("articles");
          }
    }


    async createArticle(){

        try{
        articleRef.doc().set({
            title:this.title,
            description:this.description,
            authorName:this.authorName,
            content:this.content,
            approved:false

        }).then(doc=>(
            console.log("Article written ",doc.id)
        ))
        }catch(err){
            console.log("something went wrong ",err);
        }
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