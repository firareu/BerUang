// src/routes/authRoutes.js
const express = require('express');
const router = express.Router();
const authController = require('../controllers/authController');

// Endpoint untuk register
router.post('/register', authController.register);

// Endpoint untuk login
router.post('/login', authController.login);

module.exports = router;
