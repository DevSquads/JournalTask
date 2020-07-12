const express     = require('express');
const router      =  new express.Router()
const User        = require('../model/user')

const authenticate  = require('../authenticate')

router.post('/', async (req,res) => {
  const user = new User(req.body);
  try{
    const token = await user.newAuthToken();
    res.status(201).send({user, token});
  }catch(e){
    res.status(400).send(e);
  }
});

router.get('/me', authenticate.auth ,async (req,res)=> {
  res.send(req.user);
});


router.post('/login', async (req, res) => {
  try {
    const user  = await User.checkValidCredentials(req.body.email, req.body.password);
    const token = await user.newAuthToken();
    res.send({ user, token});
  } catch (error) {
    res.status(400).send();
  }
})

router.post('/logout', authenticate.auth, async (req, res) => {
  try {
    req.user.tokens = req.user.tokens.filter((token) =>{
      return token.token !== req.token;
    });
    await req.user.save();
    res.send();
  } catch (error) {
    res.status(500).send();
  }
});


router.post('/logoutall', authenticate.auth, async (req, res) => {
  try {
    req.user.tokens = [];
    await req.user.save();
    res.send();
  } catch (error) {
    res.status(500).send();
  }
})


module.exports = router;