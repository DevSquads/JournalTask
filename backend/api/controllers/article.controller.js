var mongoose = require("mongoose"),
	Article = mongoose.model("Article");
    
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