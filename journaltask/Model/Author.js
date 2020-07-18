import firebase from "../firebase/firebase";

let userRef;

class Author {

    name;
    email;
    
    constructor(){
        if (process.browser) {
            let frbase = new firebase();
            articleRef = frbase.app.firestore().collection("authors");
          }
    }

    

    


}

let author = new Author;
export default author;