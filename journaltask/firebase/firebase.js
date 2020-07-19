//import firebase from 'firebase/app';
//import * as admin from 'firebase-admin';
// import 'firebase/auth';
// //import 'firebase/analytics';
// import 'firebase/storage';
// import "firebase/firestore";
import * as firebase from "firebase";

import firebaseConfig from './config';



// Initialize Firebase
let storageRef;

  class Firebase {
    static firebaseInstance;
    constructor(){
        
        console.log("[FIREBASE CONSTRUCTOR]", Firebase.firebaseInstance);
        if(!Firebase.firebaseInstance){

            if (!firebase.apps.length) {
                firebase.initializeApp(firebaseConfig);
            }
            
            //firebase.analytics();
            this.app = firebase;
            this.auth = firebase.auth();
            this.storage = this.app.storage();
            //this.storageRef = this.storage.ref();
            

            if(process.browser){
                window.testFbase = firebase;
            }
        }
        return Firebase.instance;

        

    }
}

export default Firebase;