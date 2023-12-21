const {
  addOutcome: addOutcomeModel,
  getOutcome: getOutcomeModel,
  getOutcomeById: getOutcomeByIdModel,
  updateOutcome: updateOutcomeModel,
  deleteOutcome: deleteOutcomeModel,
} = require("../models/outcome");

// POST
const addOutcome = async (req, res) => {
  try {
    const outcomeId = await addOutcomeModel(req.body);
    console.log("Outcome added successfully:", outcomeId);
    res.status(201).json({ outcomeId });
  } catch (error) {
    console.error("Error creating adding outcome.", error);
    res.status(500).json({ error: "Internal Server Error" });
  }
};

// GET ALL
const getOutcome = async (req, res) => {
  try {
    const userId = req.params.userId;
    const outcome = await getOutcomeModel(userId);
    if (outcome.length > 0) {
      res.status(200).json({ outcome });
    } else {
      res.status(404).json({ error: "Outcome not found" });
    }
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: "Internal Server Error " });
  }
};

// GET by Id
const getOutcomeById = async (req, res) => {
  try {
    const outcomeId = req.params.outcomeId;
    if (!outcomeId) {
      return res.status(400).json({ error: "Outcome ID is missing in the request" });
    }
    const outcome = await getOutcomeByIdModel(outcomeId);
    if (outcome) {
      res.status(200).json({ outcome });
    } else {
      res.status(404).json({ error: "Outcome not found" });
    }
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: "Internal Server Error" });
  }
};

// PUT
const updateOutcome = async (req, res) => {
  try {
    const outcomeId = req.params.outcomeId;
    await updateOutcomeModel(outcomeId, req.body);
    res.status(200).json({ message: "Outcome updated successfully!" });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: "Internal Server Error" });
  }
};

// DELETE
const deleteOutcome = async (req, res) => {
  try {
    const outcomeId = req.params.outcomeId;
    await deleteOutcomeModel(outcomeId);
    res.status(200).json({ message: "Outcome deleted successfully" });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: "Internal Server Error" });
  }
};

module.exports = {
  addOutcome,
  getOutcome,
  getOutcomeById,
  updateOutcome,
  deleteOutcome,
};
