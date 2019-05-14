const mongoose = require('mongoose')
const Schema = mongoose.Schema

const Mypage = new Schema({
    username: String,
    profileIMG: String,
    id: String,
    birth: String,
    profileIMG: String,
});

Mypage.static.create = (username,id,birth)=>{
    const mypage = new this({
        username,
        id,
        birth,
        profileIMG
    });

    // return the Promise
    return mypage.save()
}

module.exports = mongoose.model('Mypage', Mypage);