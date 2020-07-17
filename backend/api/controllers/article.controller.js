var mongoose = require("mongoose"),
    Validations = require("../utils/Validations");
	Article = mongoose.model("Article");
    

//Method to list of articles sorted by most popular authors ( who has the highest number of published articles).
module.exports.listArticlesSortedByMostPopularAuthors = function(req, res, next) {
	Article.aggregate([
        { $group: { _id: "$authorName", total: { $sum: 1 }, docs: { '$push': '$$ROOT' } } },
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

        var results = [];
        for(var i = 0; i < articles.length ; i++){
            results.push.apply(results,articles[i].docs)
        }

		res.status(200).json({
			err: null,
			msg: "Articles retrieved successfully.",
			data: results
		});
	});
};

//Method to create article.
module.exports.createArticle = function(req, res, next) {

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

//Method to delete article.
module.exports.deleteArticle = function(req, res, next) {

	var valid = req.body.title && 
				Validations.isString(req.body.title);

	if (!valid) {
		return res.status(422).json({
			err: null,
			msg: "title(String) is a required field",
			data: null
		});
	}

	Article.findOne({
		title: req.body.title.trim().toLowerCase()
	}).exec(function(err, ex) {
		if (err) {
			return next(err);
		}

		if (!ex) {
			return res.status(422).json({
				err: null,
				msg: "An article with this title does not exist.",
				data: null
			});
		}

		Article.deleteOne({
            title: req.body.title.trim().toLowerCase()
        }).exec(function(err, ex) {
            if (err) {
                return next(err);
            }
            return res.status(200).json({
                err: null,
                msg: "Article deleted successfully.",
                data: null
            });
            
        });  
	});
};