// src/controllers/authController.js
const { admin, db } = require('../config/firebaseConfig'); // Import `db` di sini

// Fungsi untuk registrasi
const register = async (req, res) => {
  const { email, password, additionalData } = req.body;

  try {
    const userRecord = await admin.auth().createUser({ email, password });

    // Periksa apakah additionalData adalah objek
    if (typeof additionalData !== 'object' || additionalData === null) {
      return res.status(400).send({ message: 'Invalid additional data format' });
    }

    await db.collection('users').doc(userRecord.uid).set(additionalData);
    res.status(201).send({ message: 'User created successfully', userId: userRecord.uid });
  } catch (error) {
    res.status(500).send({ message: 'Error creating new user', error: error.message });
  }
};

// Fungsi untuk login

const login = async (req, res) => {
    const { email, password } = req.body;
  
    try {
      // Verifikasi pengguna dengan Firebase
      const userRecord = await admin.auth().getUserByEmail(email);

      // Kirim respons sukses
      res.status(200).send({ message: 'Login successful', userId: userRecord.uid });
    } catch (error) {
      res.status(401).send({ message: 'Login failed', error: error.message });
    }
  };

module.exports = {
    register,
    login
};
