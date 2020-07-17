var mongoose = require("mongoose"),
    Validations = require("../utils/Validations");
	Article = mongoose.model("Article");


//Method to approve article.
module.exports.approveArticle = function(req, res, next) {

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
	}).exec(function(err, article) {
		if (err) {
			return next(err);
		}

		if (!article) {
			return res.status(422).json({
				err: null,
				msg: "An article with this title does not exist.",
				data: null
			});
		}
			article.approved = true;
			article.save(function(err) {
				if (err) {
					return next(err);
				}
			});

			res.status(200).json({
				err: null,
				msg: "Article was approved successfully.",
				data: article
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