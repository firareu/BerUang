const admin = require('firebase-admin');
const { getFirestore } = require('firebase-admin/firestore');

const serviceAccount = require('../../config/serviceAccountKey.json');

if (!admin.apps.length) {
    admin.initializeApp({
      credential: admin.credential.cert(serviceAccount),
      databaseURL: 'https://app-beruang-CH2-PS111.firebaseio.com',
    });
  }

const db = getFirestore();

const addIncome = async (incomeData) => {
    const incomesCollection = db.collection('incomes')
    const incomeRef = await incomesCollection.add({
      salary: incomeData.salary,
      date: incomeData.date
    });
    return incomeRef.id;
};

const getIncome = async () => {
  const incomeDoc = await db.collection('incomes').get()
  if (incomeDoc) {
    let incomeData = []
    incomeDoc.forEach(doc => {
      incomeData.push(doc.data())
    })
    return incomeData
  } else {
    console.log('Income not found');
  }
};

const getIncomeById = async (incomeId) => {
  const incomeDoc = await db.collection('incomes').doc(incomeId).get()
  if (incomeDoc.exist) {
    const incomeData = incomeDoc.data();
    return incomeData
  } else {
    console.log('Income not found');
  }
};

const updateIncome = async (incomeId, updatedIncomeData) => {
  const incomeRef = db.collection('incomes').doc(incomeId);

  await incomeRef.update({
    salary: updatedIncomeData.salary,
    date: updatedIncomeData.date
  });
  console.log('Income updated successfully!')
};

const deleteIncome = async (incomeId) => {
  const incomeRef = db.collection('incomes').doc(incomeId);
  await incomeRef.delete();
  console.log('Income deleted successfully');
};

module.exports = {
    addIncome,
    getIncome,
    getIncomeById,
    updateIncome,
    deleteIncome
};