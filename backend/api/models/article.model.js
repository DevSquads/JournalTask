var mongoose = require("mongoose");

var articleSchema = mongoose.Schema({
	title: {
		type: String,
		required: true,
		unique: true,
		trim: true
    },
    description: {
        type: String,
        required: true,
		trim: true
    },
    authorName: {
      type: String,
      required: true,
      trim: true
    }

});

mongoose.model("Article", articleSchema);
