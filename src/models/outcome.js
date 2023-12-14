const admin = require('firebase-admin');
const db = admin.firestore();

const createOutcome = async (outcomeData, userId) => {
  const outcomesCollection = db.collection('outcomes');
  const userOutcomesCollection = outcomesCollection.doc(userId).collection('user_outcomes');
  const outcomeRef = await userOutcomesCollection.add({
    id: outcomeData.id,
    amount: outcomeData.amount,
    description: outcomeData.description || null,
    category: outcomeData.category || null,
    userId: userId,  // Reference to User document (one-to-many)
  });
  return outcomeRef.id;
};

module.exports = { createOutcome };