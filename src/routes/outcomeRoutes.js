const express = require('express');
const router = express.Router();
const outcomeController = require('../controllers/outcomeController');

// POST
router.post('/add', outcomeController.addOutcome);

module.exports = router;