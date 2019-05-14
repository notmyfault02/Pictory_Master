const Mypage = require('../../../models/Mypage');
const jwt = require('jsonwebtoken');
const multer = require('multer');

const UpdateMypage = (req,res)=>{
    const token = req.headers['x-access-token'] || req.query.token;
    const tokenDecoded= jwt.verify(token, req.app.get('jwt-secret'), (err, decoded) => {
        return decoded;
    });
    
}

const ReadMypage = (req,res)=>{
    const token = req.headers['x-access-token'] || req.query.token

    let tokenDecoded= jwt.verify(token, req.app.get('jwt-secret'), (err, decoded) => {
        return decoded;
    });

    Mypage.findOne({id:tokenDecoded.id},{_id:0,id:0,__v:0},(err,mypage)=>{
        if(!mypage) res.status(404).json({messege: "Not found"});
        if(err) {
            console.log(err);
            return res.status(500).end();
        }
        
        res.json(mypage);
    });
}

module.exports = {
    UpdateMypage,
    ReadMypage,
}