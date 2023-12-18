const express = require('express');
const router = express.Router();
const incomeController = require('../controllers/incomeController');

// POST
router.post('/add', incomeController.addIncome);

//GET
router.get('/', incomeController.getIncome);
router.get('/:incomeId', incomeController.getIncomeById);

// PUT
router.put('/:incomeId', incomeController.updateIncome);

// DELETE 
router.delete('/:incomeId', incomeController.deleteIncome);

module.exports = router;
