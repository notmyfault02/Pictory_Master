const mongoose = require('mongoose')
const Schema = mongoose.Schema


const User = new Schema({
    username: String,
    id: String,
    pw: String,
    birth: Number,
    active: {type:Boolean,default:false}
});


User.statics.create = function(username,id,pw,birth) {
    const user = new this({
        username,
        id,
        pw,
        birth,
    })

    // return the Promise
    return user.save()
}

// find one user by using username
User.statics.findOneById = function(id) {
    return this.findOne({
        id
    }).exec();
}



// verify the password of the User documment
User.methods.verify = function(pw) {
    return this.pw === pw
}


module.exports = mongoose.model('User', User)