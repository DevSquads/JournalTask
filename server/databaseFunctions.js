const express = require('express');
const router = express.Router();
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser')

var admin = require("firebase-admin");
var serviceAccount = require("./ArabServiceKey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});
const db = admin.firestore();
let FieldValue = require('firebase-admin').firestore.FieldValue;

// ======= Functions ======= //

// === USERS === //

// user insertion
function createUser(obj){
  db.collection('users').doc(`user: ${obj.name}`)
.set(obj).then(()=> {
  console.log(`user: ${obj.name} inserted`);
})
}

// get certain user
function getUser(username){
db.collection('users').doc(`user:${username}`)
.get().then(doc =>{
  if (!doc.exists) {
      console.log('No such document!');
    } else {
  console.log('Document data:', doc.data())
  }
})
.catch(err => {
    console.log('Error getting document', err);
  });
};

// === CATEGORIES === //

// category creation and post insertion
function createCard(obj){
  db.collection(`${obj.category}`).doc(`${obj.id}`)
.set(obj).then(()=> {
  console.log(`card inserted`);
})
}

// get certain card in category
function getCard(collection, id) {
  db.collection(collection).doc(id)
 .get().then(doc =>{
   if (!doc.exists) {
       console.log('No such document!');
     } else {
   console.log('Document data:', doc.data())
   }
 })
 .catch(err => {
     console.log('Error getting document', err);
   });
};

// update certain card in category
function updatCard(obj){
  db.collection(`${obj.category}`).doc(`${obj.id}`)
.update({isAvailable: false});
}

// add property to a certain category post
function addProp(collection, id, updateObj){
db.collection(collection).doc(id)
.update(updateObj);
}

// delete a property from a certain category post
function deleteProp(collection, id, fieldNmae){
db.collection(collection).doc(id)
.update({
  fieldNmae: FieldValue.delete()
});
}

// delete a card from a certain category post
function deleteProp(collection, id, fieldNmae){
db.collection(collection).doc(id).delete()
}

// get certain documents with condition
function findWithCond (collection, condition) {
  db.collection(collection).where('capital', '==', true).get()
    .then(snapshot => {
      if (snapshot.empty) {
        console.log('No matching documents.');
        return;
      }

      snapshot.forEach(doc => {
        console.log(doc.id, '=>', doc.data());
      });
    })
    .catch(err => {
      console.log('Error getting documents', err);
    });
}

// get all documents in collection
function findWithCond (collection) {
  db.collection(collection).get()
    .then(snapshot => {
      if (snapshot.empty) {
        console.log('No matching documents.');
        return;
      }
      snapshot.forEach(doc => {
        console.log(doc.id, '=>', doc.data());
      });
    })
    .catch(err => {
      console.log('Error getting documents', err);
    });
}

//Testing

// asyncData({req, redirect}) {
//   console.log('hi from asyncData')
//   if(!process.server){
//     console.log('server is on')
//   } else {
//     let user = firebase.auth().currentUser;
//     console.log(user);
//     if(!user){
//       redirect("/")
//     }
//   }
// }

//createing

// createUser(user2);
// createCard(shoes1);
// createCard(shoes2);
// createCard(bag1);
// createCard(bag2);

//getting

    // <div class="custom-control custom-checkbox mt-2 mb-3">
    // <input name="bestSeller" type="checkbox" class="custom-control-input" id="defaultUnchecked">
    // <label class="custom-control-label" for="defaultUnchecked">bestSeller</label>
    // </div>
