const Article = require('../article.model');
const { User } = require('../../user');

async function getArticleByUserId(req, res, next) {
    try {
        let user = await User.findOne({ _id: req.userData._id })

        if (!user) return res.status(400).json({ message: 'Invalid user' });

        let articles = await Article.find({}).sort({ approve: -1 });
        if (user.role === 'admin') return res.status(200).json(articles);

        articles = await Article.find({});
        articles.sort((a, b) => b.addedBy.toString().localeCompare(user._id));
        return res.status(200).json(articles);

    } catch (error) {
        console.log(error);
        return res.status(500).json({ message: 'Internal server error' });
    }
}

module.exports = getArticleByUserId;