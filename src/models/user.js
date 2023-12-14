const admin = require('firebase-admin');

// Initialize Firebase app
const serviceAccount = require('../../config/app-test-beruang-firebase-adminsdk-5bkf7-46597f5aa6.json'); // Replace with the path to your service account key

if (!admin.apps.length) {
    admin.initializeApp({
      credential: admin.credential.cert(serviceAccount),
      databaseURL: 'https://app-test-beruang.firebaseio.com', // Replace with your Firebase project URL
    });
  }

const db = admin.firestore();

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

module.exports = { createUser };