var mongoose = require("mongoose"),
    Validations = require("../utils/Validations");
	Article = mongoose.model("Article");
    

//Method to list of articles sorted by most popular authors ( who has the highest number of published articles).
module.exports.listArticlesSortedByMostPopularAuthors = function(req, res, next) {
	Article.aggregate([
        { $group: { _id: "$authorName", total: { $sum: 1 } } },
        { $sort: { total: -1 } }
     ]).exec(function(err, articles) {
		if (err) {
			return next(err);
		}
		if (!articles) {
			return res
				.status(404)
				.json({ err: null, msg: "Articles not found.", data: null });
		}
		res.status(200).json({
			err: null,
			msg: "Articles retrieved successfully.",
			data: articles
		});
	});
};

//Method to create article.
module.exports.createArticle = function(req, res, next) {

    console.log("enter");

	var valid = req.body.title && 
				Validations.isString(req.body.title) &&
				req.body.description && 
				Validations.isString(req.body.description) &&
				req.body.authorName &&
				Validations.isString(req.body.authorName);

	if (!valid) {
		return res.status(422).json({
			err: null,
			msg: "title(String), description(String) and authorName(String) are required fields",
			data: null
		});
	}

	Article.findOne({
		title: req.body.title.trim().toLowerCase()
	}).exec(function(err, ex) {
		if (err) {
			return next(err);
		}

		if (ex) {
			return res.status(422).json({
				err: null,
				msg: "An article with this title already exists.",
				data: null
			});
		}

		Article.create(req.body, function(err, article) {
			if (err) {
				return next(err);
			}
			res.status(200).json({
				err: null,
				msg: "Article was created successfully.",
				data: article
			});
		});
	});
};