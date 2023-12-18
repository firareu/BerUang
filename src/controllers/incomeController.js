const { addIncome: addIncomeModel,
        getIncome: getIncomeModel,
        getIncomeById: getIncomeByIdModel,
        updateIncome: updateIncomeModel,
        deleteIncome: deleteIncomeModel } = require('../models/income');

// POST
const addIncome = async (req, res) => {
  try {
    const incomeId = await addIncomeModel(req.body);
    res.status(201).json({incomeId});
  } catch (error) {
    console.error('Error creating adding income.', error);
    res.status(500).json({error: 'Internal Server Error'});
  }
};

// GET ALL
const getIncome = async (req, res) => {
  try {
    const income = await getIncomeModel();
    console.log("this is income get all", income)
    if (income.length > 0) {
      res.status(200).json({ income });
    } else {
      res.status(404).json({ error: 'Income not found' });
    }
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error '});
  }
};

// GET by Id
const getIncomeById = async (req, res) => {
  try {
    const incomeId = req.params.incomeId;
    if (!incomeId) {
      return res.status(400).json({ error: 'Income ID is missing in the request' });
    }
    
    const income = await getIncomeByIdModel(incomeId);
    console.log("this shud be income get by id", income)
    if (income) {
      res.status(200).json({ income });
    } else {
      res.status(404).json({ error: 'Income not found'});
    }
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' })
  }
};

// PUT
const updateIncome = async (req, res) => {
  try {
    const incomeId = req.params.incomeId;
    await updateIncomeModel(incomeId, req.body);
    res.status(200).json({ message: 'Income updated successfully!' });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

// DELETE
const deleteIncome = async (req, res) => {
   try {
    const incomeId = req.params.incomeId;
    await deleteIncomeModel(incomeId);
    res.status(200).json({ message: 'Income deleted successfully' });
   } catch (error) {
    console.error(error);
    res.status(500).json({ error : 'Internal Server Error' });
   }
};

module.exports = { addIncome, 
                   getIncome,  
                   getIncomeById, 
                   updateIncome,
                   deleteIncome };