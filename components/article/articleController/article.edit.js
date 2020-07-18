const mongoose = require('mongoose');
const Article = require('../article.model');
const { articleSchema: articleValidationSchema } = require('../article.validation');

async function editArticle(req, res, next) {

    try {
        const { articleId } = req.params;
        if (!mongoose.Types.ObjectId.isValid(articleId)) return res.status(400).send({ message: 'Invalid Article' });

        const { error, value } = articleValidationSchema.validate(req.body);
        if (error) return res.status(400).json({ message: error.message.replace(/"/g, '') });

        // value.addedBy = req.userData._id;

        let editedArticle = await Article.findOneAndUpdate({ _id: articleId }, value, { new: true });

        if (!editedArticle) return res.status(400).json({ message: 'Invalid Article' });

        return res.status(200).json({ message: 'Article edited successfully', editedArticle });

    } catch (error) {
        console.log(error);
        return res.status(500).json({ message: 'Internal server error' });
    }
}

module.exports = editArticle;