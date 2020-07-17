var mongoose = require("mongoose"),
    Validations = require("../utils/Validations");
	Article = mongoose.model("Article");
    

//Method to view all articles after author identifies himself.
module.exports.viewAllArticles = function(req, res, next) {

    var valid = req.params.authorName && 
                Validations.isString(req.params.authorName);


    if (!valid) {
        return res.status(422).json({
            err: null,
            msg: "authorName(String) is a required field",
            data: null
        });
    }
                

	Article.find({ $and: [{authorName: req.params.authorName.trim()} , {approved: true}]}).exec(function(err, mathcedArticles) {
		if (err) {
			return next(err);
        }
        
    Article.find({ $and: [{authorName: {$ne: req.params.authorName.trim()}} ,{approved: true}]}).exec(function(err, notMathcedArticles) {
        if (err) {
            return next(err);
        }

        var results = [];
        results.push.apply(results,mathcedArticles);
        results.push.apply(results,notMathcedArticles);
        

		res.status(200).json({
			err: null,
			msg: "Articles retrieved successfully.",
			data: results
		});
    });
});
};