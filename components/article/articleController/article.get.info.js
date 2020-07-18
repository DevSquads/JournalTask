const mongoose = require('mongoose');
const Article = require('../article.model');


async function getArticleInfo(req, res, next) {
    try {
        const { articleId } = req.params;
        if (!mongoose.Types.ObjectId.isValid(articleId)) return res.status(400).send({ message: 'Invalid Article' });

        const article = await Article.findOne({ _id: articleId }).lean();
        if (!article) return res.status(400).send({ message: 'Invalid Article' });

        return res.status(200).json({ message: "Get Article Info Successfully", article });
    } catch (error) {
        console.log(error);
        return res.status(500).json({ message: 'Internal server error' });
    }
}

module.exports = getArticleInfo;