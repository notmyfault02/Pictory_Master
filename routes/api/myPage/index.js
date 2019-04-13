const express = require('express');
const router = express.Router();
const controller = require('./myPage.controller');
const authMiddleware = require('../../../middlewares/auth')

router.use('/',authMiddleware,controller.ReadMypage);
router.use('/update',authMiddleware,controller.UpdateMypage);

module.exports = router;