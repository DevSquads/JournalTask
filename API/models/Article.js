// Article.js

const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// Define collection and schema for Article
let Article  = new Schema({
  title: {
    type: String
  },
  description: {
    type: String
  },
},{
    collection: 'Article'
});

module.exports = mongoose.model('Article', Article);