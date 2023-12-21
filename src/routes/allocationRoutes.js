const express = require('express');
const router = express.Router();
const allocationController = require('../controllers/allocationController');

// POST
router.post('/add', allocationController.addAllocation);

//GET ALL Allocation by userId
router.get('/user/:userId', allocationController.getAllocation);
// GET Allocation by allocationId
router.get('/id/:allocationId', allocationController.getAllocationById);

// PUT
router.put('/:allocationId', allocationController.updateAllocation);

// DELETE
router.delete('/:allocationId', allocationController.deleteAllocation);

module.exports = router;
