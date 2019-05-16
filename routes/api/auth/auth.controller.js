const jwt = require('jsonwebtoken');
const User = require('../../../models/User');
const fs = require('fs');

const register = (req, res) => {
    const {
        username,
        id,
        pw,
        birth,
        active,
        profileIMG
    } = req.body

    const create = (user) => {
        if (user) {
            throw new Error('user id exists')
        } else {
            res.status(200).json({message:'success signup'});
            return User.create(username,id,pw,birth,active,profileIMG);
        }
    }

    const onError = (error) => {
        res.status(409).json({
            message: error.message
        });
    }

    User.findOneById(id)
        .then(create)
        .catch(onError)
}

const login = (req,res)=>{
    const {id,pw} = req.body;
    const secret = req.app.get('jwt-secret');

    const check = (user) => {
        
        if(!user) {
            // user does not exist
            throw new Error('You must signup');
        } else {
            // user exists, check the password
            if(user.verify(pw)) {
                // create a promise that generates jwt asynchronously
                const p = new Promise((resolve, reject) => {
                    jwt.sign(
                        {
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
                return p;
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
    register,
}