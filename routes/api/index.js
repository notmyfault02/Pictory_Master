const auth = require('./auth');
const express = require('express');
const router = express.Router();

router.use('/auth',auth);