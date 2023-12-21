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

const createUser = async (userData) => {
  const usersCollection = db.collection("users");
  const userRef = await usersCollection.add({
    profilePicture: userData.profilePicture || null,
    name: userData.name,
    dob: userData.dob,
    email: userData.email,
    password: userData.password,
    gender: userData.gender || null,
  });
  return userRef.id;
};

const getUser = async () => {
  const userDoc = await db.collection("users").get();
  if (userDoc) {
    let userData = [];
    userDoc.forEach((doc) => {
      userData.push({
        userId: doc.id,
        ...doc.data(),
      });
    });
    return userData;
  } else {
    console.log("User not found");
  }
};

const getUserById = async (userId) => {
  const userDoc = await db.collection("users").doc(userId).get();
  if (userDoc.exists) {
    const userData = userDoc.data();
    return userData;
  } else {
    console.log("User not found");
  }
};

const updateUser = async (userId, updatedUserData) => {
  const userRef = db.collection("users").doc(userId);

  await userRef.update({
    profilePicture: updatedUserData.profilePicture || null,
    name: updatedUserData.name,
    dob: updatedUserData.dob,
    email: updatedUserData.email,
    password: updatedUserData.password,
    premiumStatus: updatedUserData.premiumStatus || false,
    gender: updatedUserData.gender || null,
  });
  console.log("User updated successfully");
};

const deleteUser = async (userId) => {
  const userRef = db.collection("users").doc(userId);
  await userRef.delete();
  console.log("User deleted successfully");
};

module.exports = {
  createUser,
  getUser,
  getUserById,
  updateUser,
  deleteUser,
};