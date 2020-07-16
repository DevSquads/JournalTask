const { signup: signupValidationSchema } = require('../user.validation');
const User = require('../user.model');

async function signupAdmin(req, res) {
    console.log(req.body);
    try {
        const { error, value } = signupValidationSchema.validate(req.body);

        if (error) return res.status(400).json({ message: error.message.replace(/"/g, '') });

        let user = await User.findOne({ email: value.email });

        if (user) return res.status(409).json({ message: 'Email already exists' });

        value.role = 'admin'
        user = await User.create(value);

        const token = user.signJWT();

        return res.status(201).json(
            {
                token,
                userData: {
                    _id: user._id,
                    name: user.name,
                    email: user.email,
                    articles: user.articles
                }
            });
    } catch (error) {
        console.log(error);
        return res.status(500).send({ message: 'Internal server error' });
    }
}

module.exports = signupAdmin;