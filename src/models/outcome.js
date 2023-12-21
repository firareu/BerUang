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

const addOutcome = async (outcomeData) => {
  const outcomesCollection = db.collection("outcomes");
  const outcomeRef = await outcomesCollection.add({
    amount: outcomeData.amount,
    date: outcomeData.date,
    category: outcomeData.category,
    description: outcomeData.description,
    userId: outcomeData.userId,
    allocationId: outcomeData.allocationId
  });
  return outcomeRef.id;
};

const getOutcome = async (userId) => {
  const outcomeDoc = await db.collection("outcomes").where("userId", "==", userId).get();
  if (outcomeDoc.size > 0) {
    let outcomeData = [];
    outcomeDoc.forEach((doc) => {
      outcomeData.push({
        outcomeId: doc.id,
        allocationId: doc.data().allocationId,
        ...doc.data(),
      });
    });
    return outcomeData;
  } else {
    console.log("Oucome not found");
  }
};

const getOutcomeById = async (outcomeId) => {
  const outcomeDoc = await db.collection("outcomes").doc(outcomeId).get();
  if (outcomeDoc.exists) {
    const outcomeData = {
      outcomeId: outcomeDoc.id,
      allocationId: outcomeDoc.data().allocationId,
      ...outcomeDoc.data(), 
    };
    return outcomeData;
  } else {
    console.log("Oucome not found");
  }
};

const updateOutcome = async (outcomeId, updatedoutcomeData) => {
  const outcomeRef = db.collection("outcomes").doc(outcomeId);

  await outcomeRef.update({
    amount: updatedoutcomeData.amount,
    date: updatedoutcomeData.date,
    category: updatedoutcomeData.category,
    description: updatedoutcomeData.description
  });
  console.log("Outcome updated successfully!");
};

const deleteOutcome = async (outcomeId) => {
  const outcomeRef = db.collection("outcomes").doc(outcomeId);
  await outcomeRef.delete();
  console.log("Outcome deleted successfully");
};

module.exports = {
  addOutcome,
  getOutcome,
  getOutcomeById,
  updateOutcome,
  deleteOutcome,
};