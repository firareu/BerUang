const admin = require("firebase-admin");
const { getFirestore } = require("firebase-admin/firestore");

const serviceAccount = require("../../config/serviceAccountKey.json");

if (!admin.apps.length) {
  admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://app-beruang-CH2-PS111.firebaseio.com",
  });
}

const db = getFirestore();

const addAllocation = async (allocationData) => {
  const allocationCollection = db.collection("allocations");
  const allocationRef = await allocationCollection.add({
    category: allocationData.category,
    precentage: allocationData.precentage,
    amount: allocationData.amount,
    userId: allocationData.userId,
  });
  return allocationRef.id;
};

const getAllocation = async (userId) => {
  const allocationDoc = await db.collection("allocations").where("userId", "==", userId).get();
  if (allocationDoc.size > 0) {
    let allocationData = [];
    allocationDoc.forEach((doc) => {
      allocationData.push({
        allocationId: doc.id,
        ...doc.data(),
      });
    });
    return allocationData;
  } else {
    console.log("Allocation not found");
  }
};

const getAllocationById = async (allocationId) => {
  const allocationDoc = await db.collection("allocations").doc(allocationId).get();
  if (allocationDoc.exists) {
    const allocationData = allocationDoc.data();
    return allocationData;
  } else {
    console.log("Allocation not found");
  }
};

const updateAllocation = async (allocationId, updatedAllocationData) => {
  const allocationRef = db.collection("allocations").doc(allocationId);

  await allocationRef.update({
    category: updatedAllocationData.category,
    precentage: updatedAllocationData.precentage,
    amount: updatedAllocationData.amount,
  });
  console.log("Allocation updated successfully!");
};

const deleteAllocation = async (allocationId) => {
  const allocationRef = db.collection("allocations").doc(allocationId);
  await allocationRef.delete();
  console.log("Allocation deleted successfully");
};

module.exports = {
  addAllocation,
  getAllocation,
  getAllocationById,
  updateAllocation,
  deleteAllocation,
};