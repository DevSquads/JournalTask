const express = require('express');
const router = express.Router();
const User= require('../model/user')
const authenticate = require('../authenticate');

router.post('/signup', async (req,res) => {
  const user = new User(req.body);
  try{
    const token = await user.newAuthToken();
    res.status(201).send({user, token});
  }catch(e){
    console.log(e);
    res.status(400).send(e);
  }
});

router.post('/login', async (req, res) => {
  try {
    const user  = await User.checkValidCredentials(req.body.email, req.body.password);
    const token = await user.newAuthToken();
    res.send({ user, token});
  } catch (error) {

    res.status(400).send();
  }
});

router.post('/logout', authenticate, async (req, res) => {
  try {
    req.user.tokens = req.user.tokens.filter((token) =>{
      return token.token !== req.token
    });
    await req.user.save();
    res.send();
  } catch (error) {
    res.status(500).send();
  }
})


router.post('/logoutall', authenticate, async (req, res) => {
  try {
    req.user.tokens = [];
    await req.user.save();
    res.send();
  } catch (error) {
    res.status(500).send();
  }
})

module.exports = router;
