const express = require('express');
const router = express.Router();
const outcomeController = require('../controllers/outcomeController');

// POST
router.post('/add', outcomeController.addOutcome);

//GET ALL Outcome by userId
router.get('/', outcomeController.getOutcome);
// GET Outcome by outcomeId
router.get('/:outcomeId', outcomeController.getOutcomeById);

// PUT
router.put('/:outcomeId', outcomeController.updateOutcome);

// DELETE
router.delete('/:outcomeId', outcomeController.deleteOutcome);

module.exports = router;
