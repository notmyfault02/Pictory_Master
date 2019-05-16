const express = require('express');
const body_parser = require('body-parser');
const mogrgan = require('morgan');
const mongoose = require('mongoose');

const api = require('./routes/api');
const config = require('./config');
const port = process.env.PORT || 1212;

let app = express();

app.use(body_parser.urlencoded({extended: false}));
app.use(body_parser.json());

app.use(mogrgan('dev'));

app.set('jwt-secret', config.secret);

app.use('/api',api);

app.listen(port, () => {
    console.log(`Express server is running at ${port}`);
});

mongoose.connect(config.mongodbUri, { useNewUrlParser: true });
const db = mongoose.connection;
db.on('error', console.error);
db.once('open', () => {
    console.log('Connected to mongodb server');
});