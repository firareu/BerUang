const admin = require('firebase-admin');
const { getFirestore } = require('firebase-admin/firestore');

const serviceAccount = require('../../config/app-test-beruang-firebase-adminsdk-5bkf7-46597f5aa6.json');

if (!admin.apps.length) {
    admin.initializeApp({
      credential: admin.credential.cert(serviceAccount),
      databaseURL: 'https://app-test-beruang.firebaseio.com',
    });
  }

const db = getFirestore();

const createUser = async (userData) => {
  const usersCollection = db.collection('users');
  const userRef = await usersCollection.add({
    profilePicture: userData.profilePicture || null,
    name: userData.name,
    dob: userData.dob,
    username: userData.username,
    email: userData.email,
    password: userData.password,
    premiumStatus: userData.premiumStatus || false,
    contact: userData.contact || null,
    gender: userData.gender || null,
    incomeId: userData.incomeId || null,
  });
  return userRef.id;
};

const getUser = async () => {
  const userDoc = await db.collection('users').get()
  if (userDoc) {
    let userData = []
    userDoc.forEach(doc => {
      // console.log("doc data", doc.data())
      userData.push(doc.data())
    })
    return userData
  } else {
    console.log('User not found');
  }
} 

const getUserById = async (userId) => {
  const userDoc = await db.collection('users').doc(userId).get()
  if (userDoc.exists) {
    const userData = userDoc.data();
    return userData
  } else {
    console.log('User not found');
  }
};

const updateUser = async (userId, updatedUserData) => {
  const userRef = db.collection('users').doc(userId);

  await userRef.update({
    profilePicture: updatedUserData.profilePicture || null,
    name: updatedUserData.name,
    dob: updatedUserData.dob,
    username: updatedUserData.username,
    email: updatedUserData.email,
    password: updatedUserData.password,
    premiumStatus: updatedUserData.premiumStatus || false,
    contact: updatedUserData.contact || null,
    gender: updatedUserData.gender || null,
    incomeId: updatedUserData.incomeId || null,
  });

  console.log('User updated successfully');
};

const deleteUser = async (userId) => {
  const userRef = db.collection('users').doc(userId);
  await userRef.delete();
  console.log('User deleted successfully');
};

module.exports = {
  createUser,
  getUser,
  getUserById,
  updateUser,
  deleteUser,
};