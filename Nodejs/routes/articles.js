const router = require('express').Router();
let Article = require('../Models/article.Model');

router.route('/').post((req, res) => {
    Article.find(req.body)
    .then(articles => res.json(articles))
    .catch(err => res.status(400).json('Error: ' + err));
});

router.route('/:id').get((req, res) => {
    Article.findById(req.params.id)
      .then(article => res.json(article))
      .catch(err => res.status(400).json('Error: ' + err));
});

router.route('/delete/:id').delete((req, res) => {
    Article.findByIdAndDelete(req.params.id)
      .then(() => res.json('Article deleted.'))
      .catch(err => res.status(400).json('Error: ' + err));
});

router.route('/add').post((req,res) => {
    const article = req.body;
    const newArticle = new Article(article);

    newArticle.save()
    .then(() => res.json('Article added!'))
    .catch(err => res.status(400).json('Error: ' + err));
})

router.route('/update/:id').post((req, res) => {
    Article.findById(req.params.id)
      .then(article => {
          article.approved = req.body.approved;
        article.save()
          .then(() => res.json('Article updated!'))
          .catch(err => res.status(400).json('Error: ' + err));
      })
      .catch(err => res.status(400).json('Error: ' + err));
  });

module.exports = router;