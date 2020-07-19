import firebase from "../firebase/firebase";

let authRef;

class Author {

    name;
    email;
    
    constructor(){
        if (process.browser) {
            let frbase = new firebase();
            authRef = frbase.auth;
          }
    }


    async register(email,password,username){
        let newAuthor = authRef.createUserWithEmailAndPassword(email,password);
        //await newAuthor.user.updateProfile({displayName: username});
    }

    async login(email,password){
        return authRef
      .signInWithEmailAndPassword(email, password)
      .then((response) => {
          console.log("USER ID ",response.user.uid);
      })
    }
    

    


}

let author = new Author;
export default author;