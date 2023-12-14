const admin = require('firebase-admin');
const db = admin.firestore();

const createIncome = async (incomeData, userId) => {
  const incomesCollection = db.collection('incomes');
  const userIncomesCollection = incomesCollection.doc(userId).collection('user_incomes');
  const incomeRef = await userIncomesCollection.add({
    id: incomeData.id,
    amount: incomeData.amount,
    description: incomeData.description || null,
    userId: userId,
  });
  return incomeRef.id;
};

module.exports = { createIncome };