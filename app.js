const express = require('express')
,body_parser = require('body-parser')
,mogrgan = require('morgan')
,mongoose = require('mongoose');

const api = require('./routes/api');
const config = require('./config')
const port = process.env.PORT || 3000;
//app.use('/show',express.static('uploads'));


let app = express();
app.use(body_parser.urlencoded({extended: false}));
app.use(body_parser.json());

app.use(mogrgan('dev'));
app.set('jwt-secret', config.secret);

app.use('/api',api);

app.listen(port, () => {
    console.log(`Express server is running at ${port}`);
});

mongoose.connect(config.mongodbUri)
const db = mongoose.connection
db.on('error', console.error)
db.once('open', ()=>{
    console.log('connected to mongodb server')
})