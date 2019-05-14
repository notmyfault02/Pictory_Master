const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const Images = new Schema({
    IMG_name:String,
    id:String,
    Upload_time:{type:Date,default:Date.now()},
});

Images.static.create = (IMG_name,id)=>{
    const Images = new this({
        IMG_name,
        id,
    });

    // return the Promise
    return Images.save();
}

module.exports = mongoose.model('Images',Images);