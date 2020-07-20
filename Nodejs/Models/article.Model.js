const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const articleSchema = new Schema ({
    authorName: {type: String, required: true},
    authorID: {type: String, required: true},
    approved: {type: Boolean, required: true, default: false},
    description: {type: String, required: true},
    title: {type: String, required: true}
}, { timestamps: true, });

const article = mongoose.model("Article", articleSchema);

module.exports = article