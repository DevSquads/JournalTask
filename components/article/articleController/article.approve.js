const mongoose = require('mongoose');
const Article = require('../article.model');
const { User } = require('../../user');

async function approveArticle(req, res, next) {
  try {
    const { articleId } = req.params;
    if (!mongoose.Types.ObjectId.isValid(articleId)) return res.status(400).send({ message: 'Invalid Article' });

    let user = await User.findOne({ _id: req.userData._id })
    if (!user) return res.status(400).json({ message: 'Invalid user' });

    if (user.role !== 'admin') return res.status(400).json({ message: 'you dont have permission for this operation' });

    let approvedArticle = await Article.findOneAndUpdate({ _id: articleId }, { approve: true }, { new: true });
    if (!approvedArticle) return res.status(400).json({ message: 'Invalid Article' });

    return res.status(200).json({ message: 'Article Approved Successfully', approvedArticle });
  } catch (error) {
    console.log(error);
    res.status(500).send({ message: 'Internal server error' });
  }
}

module.exports = approveArticle;