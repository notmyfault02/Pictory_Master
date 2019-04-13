const authMiddleware = require('../../../middlewares/auth')
const controller = require('./auth.controller');
const express = require('express');
const router = express.Router();

router.post('/register',controller.register);
router.post('/login',controller.login);

module.exports = router;