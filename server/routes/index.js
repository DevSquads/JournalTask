//constants
const express = require('express');
const router = express.Router();
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser')

var admin = require("firebase-admin");
var serviceAccount = require("../JournalServiceKey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});
const db = admin.firestore();

// middelwares

//home test running ==========================

router.get('/', (req, res) => res.send('Your Journal System Is Running And Functional'))

//get all users==========================

router.get('/api/users', (req, res) => {
  console.log('inside users get api')
  db.collection('users').orderBy('counter', 'desc').get()
  .then( snapshot => {
      if (snapshot.empty) {
        console.log('No matching documents.');
        return;
      }
      let users = [];
      snapshot.forEach(doc => {
        var pDoc = { id: doc.id, data: doc.data()}
        users.push(pDoc)
      });
      // console.log(cardsArr)
      res.json({ users: users })
    })
    .catch(err => {
      console.log('Error getting documents', err);
    });
});

//get user==========================

router.post('/api/user', (req, res) => {
  console.log('inside user get api')
    let signedAuthor = req.body.signedAuthor
  db.collection('users').where('name', '==', `${signedAuthor}` ).get()
  .then( snapshot => {
      if (snapshot.empty) {
        console.log('No matching documents.');
        return;
      }
      let user = [];
      snapshot.forEach(doc => {
        var pDoc = { id: doc.id, data: doc.data()}
        user.push(pDoc)
      });
      // console.log(cardsArr)
      res.json({ user: user })
    })
    .catch(err => {
      console.log('Error getting documents', err);
    });
});

//get articles=========================

router.post('/api/articles', (req, res) => {
  console.log('inside articles get api')
  let articles = [];
  let sortedArticles = [];
  let authors = [];
  let signedAuthor = req.body.signedAuthor

  db.collection('articles').orderBy('author', 'desc').get().then(
    snapshot => {
      if (snapshot.empty) {
        console.log('No matching documents.');
        return;
      }
      snapshot.forEach(doc => {
        // console.log(doc.id, '=>', doc.data());
        var pDoc = { id: doc.id,
          author:doc.data().author,
          isPuplished:doc.data().isPuplished,
          title:doc.data().title,
          disc: doc.data().disc}
        // console.log(pDoc)
        articles.push(pDoc)
      });

      db.collection('users').orderBy('counter', 'desc').get().then(
        snapshot => {
          if (snapshot.empty) {
            console.log('No matching documents.');
            return;
          }
          snapshot.forEach(doc => {
            var pDoc = { id: doc.id,name:doc.data().name ,counter:doc.data().counter}
            authors.push(pDoc)
          });
          console.log(authors)
          console.log(articles)
          let authorArt = articles.filter((x) => { return x.author == signedAuthor });
          sortedArticles = sortedArticles.concat(authorArt)
          console.log(authorArt);
          for(let i = 0; i< authors.length; i++){
            console.log("authors Name:",authors[i].name)
            console.log("signedAuthor:",signedAuthor)
            console.log(authors[i].name != signedAuthor)

            if(authors[i].name != signedAuthor){
              let tempArt = articles.filter((x) => { return x.author == authors[i].name });
              sortedArticles = sortedArticles.concat(tempArt)
            }
          }
          res.json({ articles: sortedArticles })
        })
        .catch(err => {
          console.log('Error getting documents', err);
        });
    })
    .catch(err => {
      console.log('Error getting documents', err);
    });

});

//get puplished articles=========================

router.get('/api/puplishedArticles', (req, res) => {
  console.log('inside articles get api')
  db.collection('articles').where('isPuplished', '==', true).orderBy("publishedAt","desc").get()
    .then(snapshot => {
      if (snapshot.empty) {
        console.log('No matching documents.');
        return;
      }
      let articles = [];
      snapshot.forEach(doc => {
        // console.log(doc.id, '=>', doc.data());
        var pDoc = { id: doc.id, data: doc.data()}
        // console.log(pDoc)
        articles.push(pDoc)
      });
      // console.log(cardsArr)
      res.json({ articles: articles })
    })
    .catch(err => {
      console.log('Error getting documents', err);
    });
});


//Add article=========================

router.post('/api/addArticle', (req, res) => {
console.log('inside add article post api')
let obj = {
  title : req.body.title,
  disc : req.body.disc,
  author: req.body.author,
  isPuplished: false,
}
console.log(obj);
  db.collection(`articles`).add(obj).then(()=> {
  console.log(`article inserted in database`);
  }).catch(err => {
      console.log('Error inserting document', err);
      res.json('error');
    });

});

//delete Article=========================

router.post('/api/deleteArticle', (req, res) => {
  let id = req.body.articleId
  let author = req.body.author
  let status = req.body.status
  let userCounter;
  let userId ;
  console.log(id)
  console.log(author)

  console.log(id)
  db.collection(`articles`).doc(id).delete()
  .then(() => {
    console.log('deleted');
    console.log(`status of article is: ${status}`);
    if(status == 'true'){
      db.collection('users').where('name', '==', `${author}`).get()
        .then(snapshot => {
          if (snapshot.empty) {
            console.log('No matching documents.');
            return;
          }
          let docs = [];
          snapshot.forEach(doc => {
            var pDoc = { id: doc.id, counter: doc.data().counter}
            docs.push(pDoc)
          });
          userCounter = docs[0].counter
          userId = docs[0].id
          console.log(userCounter)
          console.log(userId)
          db.collection(`users`).doc(userId).update({counter: userCounter-1 })
        })
        .catch(err => {
          console.log('Error getting documents', err);
        });
    }
  })
   .catch(err => {
       console.log('Error deleting document', err);
       res.json('error');
     });
});

//publish Article=========================

router.post('/api/puplishArticle', (req, res) => {
  let id = req.body.articleId
  let authorName = req.body.authorName
  let userCounter;
  let userId ;
  console.log(id)
  console.log(authorName)

  db.collection(`articles`).doc(id).update({isPuplished: true,publishedAt:new Date()})
  .then(() => {
    console.log('updated');
    db.collection('users').where('name', '==', `${authorName}`).get()
      .then(snapshot => {
        if (snapshot.empty) {
          console.log('No matching documents.');
          return;
        }
        let docs = [];
        snapshot.forEach(doc => {
          var pDoc = { id: doc.id, counter: doc.data().counter}
          docs.push(pDoc)
        });
        userCounter = docs[0].counter
        userId = docs[0].id
        console.log(userCounter)
        console.log(userId)
        db.collection(`users`).doc(userId).update({counter: userCounter+1 })


      })
      .catch(err => {
        console.log('Error getting documents', err);
      });
  })
   .catch(err => {
       console.log('Error updating document', err);
       res.json('error');
     });
});

//exporting routs=========================
module.exports = router;
