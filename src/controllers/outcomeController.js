const { 
  createOutcome,
  getOutcomeById: getOutcomeByIdModel,
  updateOutcome: updateOutcomeModel,
  deleteOutcome: deleteOutcomeModel
} = require('../models/outcome');

// POST
const addOutcome = async (req, res) => {
  try {
    const userId = '1';
    const outcomeId = await createOutcome(req.body, userId);
    res.status(201).json({ outcomeId });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

// GET
const getOutcomeById = async (req, res) => {
  try {
    const outcomeId = req.params.outcomeId;
    const outcome = await getOutcomeByIdModel(outcomeId);
    res.status(200).json({ outcome });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

// PUT
const updateOutcome = async (req, res) => {
  try {
    const outcomeId = req.params.outcomeId;
    await updateOutcomeModel(outcomeId, req.body);
    res.status(200).json({ message: 'Outcome updated successfully' });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

// DELETE
const deleteOutcome = async (req, res) => {
  try {
    const outcomeId = req.params.outcomeId;
    await deleteOutcomeModel(outcomeId);
    res.status(200).json({ message: 'Outcome deleted successfully' });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

module.exports = { addOutcome, getOutcomeById, updateOutcome, deleteOutcome };
