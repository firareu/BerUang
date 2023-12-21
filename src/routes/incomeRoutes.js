const express = require('express');
const router = express.Router();
const incomeController = require('../controllers/incomeController');

// POST
router.post('/add', incomeController.addIncome);

//GET ALL Income by userId
router.get('/:userId', incomeController.getIncome);
// GET Income by incomeId
router.get('/:incomeId', incomeController.getIncomeById);

// PUT
router.put('/:incomeId', incomeController.updateIncome);

// DELETE
router.delete('/:incomeId', incomeController.deleteIncome);

module.exports = router;
