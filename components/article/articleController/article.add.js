const Article = require('../article.model');
const { articleSchema: articleValidationSchema } = require('../article.validation');
const { User } = require('../../user');

async function addArticle(req, res) {
    try {
        const { error, value } = articleValidationSchema.validate(req.body);
        if (error) return res.status(400).json({ message: error.message.replace(/"/g, '') });

        value.addedBy = req.userData._id;

        article = await Article.create(value);
        await User.updateOne({ _id: req.userData._id }, { $push: { articles: article._id } });
        return res.status(200).json({
            message: 'Article add Successfully',
            article
        });
    } catch (error) {
        console.log(error);
        return res.status(500).json({ message: 'Internal server error' });
    }
}

module.exports = addArticle;