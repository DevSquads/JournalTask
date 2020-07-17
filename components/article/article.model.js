const mongoose = require('mongoose');

const ArticleSchema = new mongoose.Schema({

    title: { type: String, required: true },
    description: { type: String },
    addedBy: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
    approve: { type: Boolean, default: false }

}, { timestamps: true, usePushEach: true });

module.exports = mongoose.model('Article', ArticleSchema);