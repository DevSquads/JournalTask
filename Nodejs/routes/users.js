const router = require('express').Router();
let User = require('../Models/user.Model');

router.route('/').post((req, res) => {
    User.find(req.body)
      .then(user => res.json(user))
      .catch(err => res.status(400).json('Error: ' + err));
});

router.route('/add').post((req,res) => {
    const user = req.body;
    const newUser= new User(user);

    newUser.save()
    .then(() => res.json('User added!'))
    .catch(err => res.status(400).json('Error: ' + err));
})

module.exports = router;