const { createIncome,
        getIncomeById: getIncomeByIdModel,
        updateIncome: updateIncomeModel,
        deleteIncome: deleteIncomeModel } = require('../models/income');

// POST
const addIncome = async (req, res) => {
  try {
    const userId = '1';
    const incomeId = await createIncome(req.body, userId);
    res.status(201).json({ incomeId });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

const getIncomeById = async (req, res) => {
  try {
    const incomeId = req.params.incomeId;
    const outcome = await getIncomeByIdModel(incomeId);
    res.status(200).json({ outcome });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

module.exports = { addIncome, getIncomeById };