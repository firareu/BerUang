const express = require('express');
const router = express.Router();
const userController = require('../controllers/userController');

// POST
router.post('/create', userController.createUser);

// GET
router.get('/users/:userId', userController.getUserById);

module.exports = router;