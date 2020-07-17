const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const Config = require('./config');
const verifyToken = require('./middlewares/verifyToken')
const { userAPI } = require('./components/user');
const { ArticleAPI } = require('./components/article');


const app = express();
const PORT = process.env.PORT || 3000;

// database connection
mongoose.connect(Config.dbURI, { useUnifiedTopology: true, useNewUrlParser: true, useCreateIndex: true, useFindAndModify: false }).then(() => {
    console.log('success connect to db');
})

// cors
app.use(cors());

// body parser
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());


// set port
app.set('port', PORT);

// passing routes
app.use('/api', verifyToken, userAPI);
app.use('/api', verifyToken, ArticleAPI);

//Capture All 404 errors
app.use(function (req, res, next) {
    res.status(404).send('Unable to find the requested resource!');
});

const server = require('http').createServer(app);
server.listen(app.get('port'));

module.exports = server;