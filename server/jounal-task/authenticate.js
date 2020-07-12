const jwt  = require('jsonwebtoken')
const User = require('./model/user')

exports.auth = async (req,res,next) => {
    try {
        const token = req.header('Authorization').replace('Bearer', '').trim()

        const decoded  = jwt.verify(token, 'secret')

        const user  = await User.findOne({ _id:decoded._id, 'tokens.token': token})

        if(!user){
            throw new Error();
        }
        req.token = token
        req.user = user
        next()
    } catch (error) {
        console.log(error)
        res.status(401).send({error:'Please authenticate!'})
    }
}

exports.verifyAdmin = (req, res, next) => {
    User.findOne({_id: req.user._id})
        .then((user) => {
            if(user.admin) {
                next();
            }else {
                err = new Error('You are not authorized to perform this operation!');
                err.status = 403;
                return next(err);
            }
        }, (err) => next(err))
        .catch(err => next(err));
};