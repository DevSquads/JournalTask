var express = require("express"),
	router = express.Router(),
	express = require('express');

	//importing controllers
	articleCtrl = require("../controllers/article.controller");
	adminCtrl = require("../controllers/admin.controller");
	authorCtrl = require("../controllers/author.controller");

//-----------------------------Article Routes-------------------------
router.get("/article/listArticlesSortedByMostPopularAuthors", articleCtrl.listArticlesSortedByMostPopularAuthors);
router.post("/article/createArticle", articleCtrl.createArticle);

//----------------------------Admin Routes----------------------------------
router.patch("/admin/approveArticle", adminCtrl.approveArticle);
router.delete("/admin/deleteArticle", adminCtrl.deleteArticle);

//----------------------------Author Routes----------------------------------
router.get("/author/viewAllArticles/:authorName", authorCtrl.viewAllArticles);

module.exports = router;
