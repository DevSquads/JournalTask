
var request = require('request-promise');
var cors = require('cors');
var express = require('express');
var bodyParser = require('body-parser');

var app = express();
var server = app.listen('8081',function(){console.log('listening to port 8081');});

app.use(bodyParser.json());
app.use(cors()) 
app.get('/showArticles',getData);

function getData(req,res) {
    const fs = require('fs');
    let rawdata = fs.readFileSync('database.json');
    let data = JSON.parse(rawdata);
    res.send(data)
}

app.post('/createArticle',updateData);

function updateData(req,res) {
    const fs = require('fs');
    let rawdata = fs.readFileSync('database.json');
    let data = JSON.parse(rawdata);
    data["Database"].push(req.body)
    data = JSON.stringify(data);
    fs.writeFileSync('database.json', data);
}
app.post('/deleteArticle',deleteData);

function deleteData(req,res) {
    const fs = require('fs');
    let rawdata = fs.readFileSync('database.json');
    let data = JSON.parse(rawdata);
    const index = data["Database"].findIndex(x=>JSON.stringify(x)===JSON.stringify(req.body));
    if (index > -1) {
        data["Database"].splice(index, 1);
    }
    data = JSON.stringify(data);
    fs.writeFileSync('database.json', data);
    res.send(data)
}
