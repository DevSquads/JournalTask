// App constans
const express = require('express');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser')
const path = require('path');
const app = express();
const port = 8080 ;

// calling helpers
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:false}));
//parsing body of the request to use

app.use(cookieParser());
// parsing cookies to use

app.listen(port, () => {
  console.log(`server is running on port: ${port}`)
});
// listening to the port

app.use(require('./routes/index.js'));
// imporing routers middelwares
