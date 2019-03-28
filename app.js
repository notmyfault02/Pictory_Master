const express = require('express');
const body_parser = require('body-parser');
const mogrgan = require('morgan');
const mongoose = require('mongoose');

const api = require('./routes/api');
const port = process.env.PORT || 3000;

let app = express();
app.use(body_parser.urlencoded({extended: false}));
app.use(body_parser.json());

app.use(mogrgan('dev'));

app.use('/api',api);

app.listen(port, () => {
    console.log(`Express server is running at ${port}`);
});

const db = mongoose.connection;
db.on('error', console.error);
db.once('open', () => {
    console.log('Connected to mongodb server');
});

mongoose.connect('mongodb://gkrud:kk881542@ds227146.mlab.com:27146/pictory_master');