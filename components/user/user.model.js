const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const config = require('../../config');

const userSchema = new mongoose.Schema({
    name: { type: String, required: true },
    email: { type: String, required: true, unique: true },
    password: { type: String, required: true },
    articles: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Article' }],
    role: { type: String, default: 'user' },

}, { timestamps: true });

userSchema.pre('save', async function (next) {
    if (!this.isModified('password')) return next();
    this.password = await bcrypt.hash(this.password, 10);
});



// check Password Validation
userSchema.methods.isPasswordValid = async function (password) {
    return await bcrypt.compare(password, this.password);
};

userSchema.methods.signJWT = function () {
    return jwt.sign({ _id: this._id }, config.JWTsecret, { expiresIn: `${config.TokenDurationInHours}h` });
};


module.exports = mongoose.model('User', userSchema);