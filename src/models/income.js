const admin = require('firebase-admin');
const db = admin.firestore();

const createIncome = async (incomeData, userId) => {
  const incomesCollection = db.collection('incomes');
  const userIncomesCollection = incomesCollection.doc(userId).collection('user_incomes');

  // Check if incomeData and incomeData.description are defined
  const data = {
    amount: incomeData.amount,
    description: incomeData.description !== undefined ? incomeData.description : null,
    userId: userId,
  };

  const incomeRef = await userIncomesCollection.add(data);
  return incomeRef.id;
};

const getIncomeById = async (userId, incomeId) => {
  const incomeDoc = await db.collection('incomes').doc(userId).collection('user_incomes').doc(incomeId).get();

  if (incomeDoc.exists) {
    const incomeData = incomeDoc.data();
    console.log(incomeData);
  } else {
    console.log('Income not found');
  }
};


module.exports = { createIncome, getIncomeById };