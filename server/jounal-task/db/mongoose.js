const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost:27017/journalTask',{
    useNewUrlParser: true,
    useCreateIndex: true
}).then(() =>{
    console.log('Connection to database is OK');
}).catch((err) =>{
    console.log('Error in Connection to database: ', err);
});