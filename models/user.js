const mongoose = require('mongoose')
const Schema = mongoose.Schema


const User = new Schema({
    username: String,
    id: String,
    pw: String,
    birth: String,
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
User.statics.findOneByEmail = function(id) {
    return this.findOne({
        id
    }).exec()
}



// verify the password of the User documment
User.methods.verify = function(password) {
    return this.password === password
}


module.exports = mongoose.model('User', User)