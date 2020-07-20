const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
require('dotenv').config();

const app = express();
const port = process.env.PORT || 5000;

app.use(cors());
app.use(express.json());

const uri = "mongodb+srv://Tamer108:tamer.108@tamer.w7mty.gcp.mongodb.net/test?retryWrites=true&w=majority";
mongoose.connect(uri, { useNewUrlParser: true, useCreateIndex: true, useUnifiedTopology: true });
const connection = mongoose.connection
connection.once('open', () => {
    console.log('connection is done');
});

const articlesRoutes = require('./routes/articles')
const usersRoutes = require('./routes/users')

app.use('/articles', articlesRoutes);
app.use('/users', usersRoutes);

app.listen(port, () => {
    console.log(`Server is running on port: ${port}`)
});