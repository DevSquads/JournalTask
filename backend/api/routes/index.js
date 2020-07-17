var express = require("express"),
	router = express.Router(),
	express = require('express');

	//importing controllers
	articleCtrl = require("../controllers/article.controller");

//-----------------------------Article Routes-------------------------
router.get("/article/listArticlesSortedByMostPopularAuthors", articleCtrl.listArticlesSortedByMostPopularAuthors);
router.post("/article/createArticle", articleCtrl.createArticle);
router.delete("/article/deleteArticle", articleCtrl.deleteArticle);

module.exports = router;
