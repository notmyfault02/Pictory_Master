const auth = require('./auth');
const Mypage = require('./myPage');
const router = require('express').Router();

router.use('/docs', require('./docs'));

router.use('/auth',auth);
router.use('/Mypage',Mypage);

module.exports = router;