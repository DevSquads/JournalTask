const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const userSchema = new Schema ({
    userName: {type: String, required: true, unique:true},
    userType: {type: Number, required: true}
}, { timestamps: true, });

const user = mongoose.model("User",  userSchema);

module.exports = user