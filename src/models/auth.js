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

const registerUser = async (userData) => {
  try {
    const { email, password, profilePicture, name, dob, gender } = userData;
    const userRecord = await admin.auth().createUser({
      email,
      password,
    });

    const usersCollection = db.collection("users");
    const userDoc = await usersCollection.add({
      uid: userRecord.uid,
      profilePicture: profilePicture || null,
      name,
      dob,
      email,
      gender: gender || null,
    });

    const userId = userDoc.id;

    return { userId };
  } catch (error) {
    console.error("Error during user registration:", error);
    throw error;
  }
};

const loginUser = async (email, password) => {
  try {
    const userRecord = await admin.auth().getUserByEmail(email);

    await admin.auth().updateUser(userRecord.uid, {
      password: password,
    });

    const usersCollection = db.collection("users");
    const userSnapshot = await usersCollection.where("uid", "==", userRecord.uid).get();

    if (!userSnapshot.empty) {
      const userDoc = userSnapshot.docs[0];
      const userId = userDoc.id;
      return { userId };
    } else {
      console.error("User data not found in Firestore.");
      return null;
    }
  } catch (error) {
    console.error("Error during login:", error);
    return null;
  }
};

module.exports = {
  registerUser,
  loginUser,
};