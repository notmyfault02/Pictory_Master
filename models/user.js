const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const User = new Schema({
    email: String,
    pw: String,
    birth: Number,
    gender: String,
});

//user 정보 DB에 저장
User.static.create = (email,pw,birth,gender)=>{
    const user= new this({
        email,
        pw,
        birth,
        gender
    });
    return user.save();
}