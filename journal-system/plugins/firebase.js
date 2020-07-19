import * as firebase from 'firebase/app'
import 'firebase/auth'
// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyCsiHPD9hgX3RCdmPLcvQM7Kim5rEKFLHo",
  authDomain: "journal-feb8d.firebaseapp.com",
  databaseURL: "https://journal-feb8d.firebaseio.com",
  projectId: "journal-feb8d",
  storageBucket: "journal-feb8d.appspot.com",
  messagingSenderId: "663117669642",
  appId: "1:663117669642:web:21bd5cc4850474298246f3",
  measurementId: "G-QL93ZV39LG"
};
// Initialize Firebase
let app = null;
if (!firebase.apps.length){
  firebase.initializeApp(firebaseConfig);
}

export default firebase
