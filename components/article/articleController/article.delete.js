const mongoose = require('mongoose');
const Article = require('../article.model');
const { User } = require('../../user');

async function deleteArticle(req, res, next) {
    try {
        const { articleId } = req.params;
        if (!mongoose.Types.ObjectId.isValid(articleId)) return res.status(400).send({ message: 'Invalid Article' });

        let user = await User.findOne({ _id: req.userData._id })
        if (!user) return res.status(400).json({ message: 'Invalid user' });

        if (user.role !== 'admin') return res.status(400).json({ message: 'you dont have permission for this operation' });

        const article = await Article.findOneAndDelete({ _id: articleId });

        if (!article) return res.status(400).json({ message: 'Invalid Article' });

        await User.updateOne({ _id: article.addedBy }, { $pull: { articles: article._id } })

        return res.status(200).json({ message: 'Articles Deleted Successfully', article });
    } catch (error) {
        console.log(error);
        res.status(500).send({ message: 'Internal server error' });
    }
}

module.exports = deleteArticle;