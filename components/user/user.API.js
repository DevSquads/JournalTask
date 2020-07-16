const express = require('express');
const { login, signup, signupAdmin } = require('./userControllers');

const router = express.Router();

router.post('/user/signup/admin', signupAdmin);
router.post('/user/signup', signup);
router.post('/user/login', login);


module.exports = router;