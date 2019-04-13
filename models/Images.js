const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const Images = new Schema({
    IMG_name:String,
    id:String,
    Upload_time:{type:Date,default:Date.now()}
});

module.exports = mongoose.model('Images',Images);