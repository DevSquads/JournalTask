const Article = require('../article.model');
const { User } = require('../../user');

async function getArticlesSortedByMostPopularAuthorsOrAuthorvViewAllArticles(req, res, next) {
    try {
        let user = await User.findOne({ _id: req.userData._id })

        if (!user) return res.status(400).json({ message: 'Invalid user' });

        const pipeline = [
            { $match: { approve: true } },
            { $group: { _id: "$addedBy", total: { $sum: 1 }, articles: { '$push': "$$ROOT" } } },
            { $sort: { total: -1 } },
            { $unwind: "$articles" },
            { $replaceRoot: { newRoot: "$$ROOT.articles" } },
        ]
        let articles = await Article.aggregate(pipeline);
        //if user role admin //samir example
        if (user.role === 'admin') return res.status(200).json(articles);

        //author
        articles = await Article.find({});
        articles.sort((a, b) => b.addedBy.toString().localeCompare(user._id));
        return res.status(200).json(articles);

    } catch (error) {
        console.log(error);
        return res.status(500).json({ message: 'Internal server error' });
    }
}

module.exports = getArticlesSortedByMostPopularAuthorsOrAuthorvViewAllArticles;