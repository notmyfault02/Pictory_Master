const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const User = new Schema({
    username: String,
    id: String,
    pw: String,
    birth: Number,
    active:Boolean,
    profileIMG:String
});

User.statics.create = function(username,id, pw,birth,active,profileIMG) {
    // const encrypted = crypto.createHmac('sha1', config.secret)
    //                   .update(paw)
    //                   .digest('base64')
    const user = new this({
        username,
        id,
        pw,
        birth,
        active,
        profileIMG
    });
    return user.save();
}

User.statics.findOneById = function(id) {
    return this.findOne({
        id
    }).exec();
}

User.methods.verify = function(pw) {
    // const encrypted = crypto.createHmac('sha1', config.secret)
    //                   .update(password)
    //                   .digest('base64')

    return this.pw=== pw
}

module.exports = mongoose.model('User', User);