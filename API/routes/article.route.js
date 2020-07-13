const express = require('express');
const app = express();
const articleRoutes = express.Router();

// Require Article model in our routes module
let Article  = require('../models/Article');

// Defined store route
articleRoutes.route('/create').post(function (req, res) {
  let article = new Article(req.body);
  article.save()
    .then(article => {
      res.status(200).json({'Article': 'Article has been added successfully and is waiting to get approved'});
    })
    .catch(err => {
    res.status(400).send("unable to save to database");
    });
});

// Defined get data(index or listing) route
articleRoutes.route('/').get(function (req, res) {
  Article.find(function (err, articles){
    if(err){
      console.log(err);
    }
    else {
      res.json(articles);
    }
  });
});

// Defined edit route
articleRoutes.route('/edit/:id').get(function (req, res) {
  let id = req.params.id;
  Article.findById(id, function (err, article){
      res.json(article);
  });
});

//  Defined update route
articleRoutes.route('/update/:id').post(function (req, res) {
  Article.findById(req.params.id, function(err, article) {
    if (!article)
      res.status(404).send("Record not found");
    else {
      article.title = req.body.title;
      article.description = req.body.description;

      article.save().then(article => {
          res.json('Update complete');
      })
      .catch(err => {
            res.status(400).send("unable to update the database");
      });
    }
  });
});

// Defined delete | remove | destroy route
articleRoutes.route('/delete/:id').get(function (req, res) {
    Article.findByIdAndRemove({_id: req.params.id}, function(err, article){
        if(err) res.json(err);
        else res.json('Successfully removed');
    });
});

module.exports = articleRoutes;