const jwt = require('jsonwebtoken');
const User = require('../../../models/User');
const Mypage = require('../../../models/Mypage');
const Image = require('../../../models/Images');


const register = (req, res) => {
    const {
        username,
        id,
        pw,
        birth,
        profileIMG
    } = req.body

    const Usercreate = (user) => {
        try{
            if (user) {
                throw new Error('user id exists')
            } 
            else {
                if(username=='') throw new Error('username is not');
                if(id=='') throw new Error('id is not');
                if(pw=='') throw new Error('pw is not');
                if(birth=='')throw new Error('birth is not');

                return User.create(username,id,pw,birth,profileIMG);
            }
        }
        catch(e){

        }
        
    }

    const Mypagecreate = (user)=>{
        const {username, id, birth,profileIMG} = user;
        Image.create(profileIMG,id);
        return Mypage.create(username,id,birth,profileIMG);
    }

    const onError = (error) => {
        res.status(409).json({
            message: error.message
        });
    }

    User.findOneById(id)
        .then(Usercreate)
        .then(Mypagecreate)
        .catch(onError)
}



const login = (req,res)=>{
    const {id,pw,active} = req.body;
    const secret = req.app.get('jwt-secret');

    const check = (user) => {
        
        if(!user) {
            // user does not exist
            throw new Error('You must signup');
        } else {
            // user exists, check the password
            if(user.verify(pw)) {
                user.active = active;
                user.save((err)=>{
                    if(err) throw new Error(err);
                });
                console.log(user);
                // create a promise that generates jwt asynchronously
                return new Promise((resolve, reject) => {
                    jwt.sign(
                        {
                            username:user.username,
                            active:user.active,
                            id: user.id,
                            pw:user.pw,
                        }, 
                        secret, 
                        {
                            expiresIn: '24h',
                            issuer: 'me',
                            subject: 'user_info'
                        }, (err, token) => {
                            if (err) reject(err)
                            resolve(token)
                        });
                }); 
            } else {
                throw new Error('login failed');
            }
        }
    }

    // respond the token 
    const respond = (token) => {        
        res.json({
            message: 'logged in successfully',
            token,
        });
    }

    // error occured
    const onError = (error) => {
        res.status(403).json({
            message: error.message
        });
    }

    // find the user
    User.findOneById(id)
    .then(check)
    .then(respond)
    .catch(onError)
}

module.exports = {
    login,
    register
}