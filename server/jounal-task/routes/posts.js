const express       = require('express');
const router        = new express.Router();
const Post          = require('../model/post');
const {ObjectID}    = require('mongodb');
const  authenticate = require('../authenticate');

router.post('/',authenticate.auth, async (req,res) => {
    const post =  new Post({
        ...req.body,
        author: req.user._id
    });
    try {
        await post.save();
        res.status(201).send(post);
    } catch (error) {
        res.status(400).send(error);
    }
});

router.get('/',authenticate.auth, async (req,res) => {
    const match = {};
    const sort  = {};

    if(req.query.published){
        match.published = req.query.published === 'true';
    }

    if(req.query.sortBy && req.query.OrderBy){
        sort[req.query.sortBy]   = req.query.OrderBy === 'desc' ? -1 : 1;
    }

    try {
        await req.user.populate({
            path:'posts',
            match,
            options:{
                limit: parseInt(req.query.limit),
                skip: parseInt(req.query.skip),
                sort
            }
        }).execPopulate();
        res.send(req.user.posts);
    } catch (error) {
        res.status(500).send();
    }
});

router.get('/:id',authenticate.auth, async (req,res) => {
    const _id =  req.params.id;
    if (!ObjectID.isValid(_id)) {
        return res.status(404).send();
    }
    try {
        const post = await Post.findOne({ _id, author: req.user._id });
        if(!post){
            return res.status(404).send();
        }
        res.send(post);
    } catch (error) {
        res.status(500).send();
    }
});
router.patch('/:id',authenticate.auth, authenticate.verifyAdmin, async (req, res) => {
    const _id = req.params.id;
    const updates = Object.keys(req.body);
    const allowedUpdates = ["published"];
    const isValidOperation  = updates.every((update) => allowedUpdates.includes(update));
    if(!isValidOperation){
        res.status(400).send({error:'Invalid updates'});
    }
    if (!ObjectID.isValid(_id)) {
        res.status(404).send();
    }
    try {
        const post = await Post.findOne({_id: req.params.id});
        if(!post){
            res.status(404).send();
        }
        updates.forEach((update) => post[update] = req.body[update]);
        await post.save();
        res.send(post);
    } catch (error) {
        res.status(400).send();
    }
});

router.delete('/:id', authenticate.auth, authenticate.verifyAdmin,async (req,res) => {
    const _id = req.params.id;
    if (!ObjectID.isValid(_id)) {
        return res.status(404).send();
    }
    try {
        const deletepost = await Post.findOneAndDelete({_id:_id, author: req.user._id});
        if (!deletepost) {
            return res.status(404).send();
        }
        res.send(deletepost);
    } catch (error) {
        res.status(500).send();
    }
});

module.exports = router;