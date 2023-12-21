const {
    addAllocation: addAllocationModel,
    getAllocation: getAllocationModel,
    getAllocationById: getAllocationByIdModel,
    updateAllocation: updateAllocationModel,
    deleteAllocation: deleteAllocationModel,
  } = require("../models/allocation");
  
  // POST
  const addAllocation = async (req, res) => {
    try {
      const allocationId = await addAllocationModel(req.body);
      console.log("Allocation added successfully:", allocationId);
      res.status(201).json({ allocationId });
    } catch (error) {
      console.error("Error creating adding allocation.", error);
      res.status(500).json({ error: "Internal Server Error" });
    }
  };
  
  // GET ALL
  const getAllocation = async (req, res) => {
    try {
      const userId = req.params.userId;
      const allocation = await getAllocationModel(userId);
      if (allocation.length > 0) {
        res.status(200).json({ allocation });
      } else {
        res.status(404).json({ error: "Allocation not found" });
      }
    } catch (error) {
      console.error(error);
      res.status(500).json({ error: "Internal Server Error " });
    }
  };
  
  // GET by Id
  const getAllocationById = async (req, res) => {
    try {
      const allocationId = req.params.allocationId;
      if (!allocationId) {
        return res.status(400).json({ error: "Allocation ID is missing in the request" });
      }
  
      const allocation = await getAllocationByIdModel(allocationId);
      console.log("this shud be allocation get by id", allocation);
      if (allocation) {
        res.status(200).json({ allocation });
      } else {
        res.status(404).json({ error: "Allocation not found" });
      }
    } catch (error) {
      console.error(error);
      res.status(500).json({ error: "Internal Server Error" });
    }
  };
  
  // PUT
  const updateAllocation = async (req, res) => {
    try {
      const allocationId = req.params.allocationId;
      await updateAllocationModel(allocationId, req.body);
      res.status(200).json({ message: "Allocation updated successfully!" });
    } catch (error) {
      console.error(error);
      res.status(500).json({ error: "Internal Server Error" });
    }
  };
  
  // DELETE
  const deleteAllocation = async (req, res) => {
    try {
      const allocationId = req.params.allocationId;
      await deleteAllocationModel(allocationId);
      res.status(200).json({ message: "Allocation deleted successfully" });
    } catch (error) {
      console.error(error);
      res.status(500).json({ error: "Internal Server Error" });
    }
  };
  
  module.exports = { addAllocation, getAllocation, getAllocationById, updateAllocation, deleteAllocation };
  