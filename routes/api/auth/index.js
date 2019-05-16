const authMiddleware = require('../../../middlewares/auth')
const controller = require('./auth.controller');
const express = require('express');
const router = express.Router();
const multer = require('multer');

let _storage = multer.diskStorage({
    destination: function (req, file, cb) {
      cb(null, 'Images');
    },
    filename: function (req, file, cb) {
      cb(null, file.originalname);
    }
});
let upload = multer({ storage: _storage });

router.post('/register',upload.single('myfile'),controller.register);
router.post('/login',controller.login);

module.exports = router;