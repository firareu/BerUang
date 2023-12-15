const express = require('express');
const router = express.Router();
const userController = require('../controllers/userController');

// POST
router.post('/create', userController.createUser);

// GET
router.get('/', userController.getUser)
router.get('/:userId', userController.getUserById);

// PUT
router.put('/:userId', userController.updateUser);

// DELETE
router.delete('/:userId', userController.deleteUser);

module.exports = router;