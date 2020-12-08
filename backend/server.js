
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
    console.log(req.body)
    const fs = require('fs');
    let rawdata = fs.readFileSync('database.json');
    let data = JSON.parse(rawdata);
    data["Database"].push(req.body)
    console.log(data["Database"])
    data = JSON.stringify(data);
    fs.writeFileSync('database.json', data);
}
