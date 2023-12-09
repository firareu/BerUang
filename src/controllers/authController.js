// src/controllers/authController.js
const admin = require('../config/firebaseConfig');

// Fungsi untuk registrasi
const register = async (req, res) => {
    const { email, password } = req.body;
    try {
        // Membuat user baru di Firebase Authentication
        const userRecord = await admin.auth().createUser({
            email: email,
            password: password
        });

        // Menambahkan data user ke Firestore
        const userData = {
            email: email,
            createdAt: new Date() // Menyimpan waktu pembuatan
        };
        await db.collection('users').doc(userRecord.uid).set(userData);

        res.status(201).send({ message: "User created successfully", uid: userRecord.uid });
    } catch (error) {
        res.status(500).send({ error: error.message });
    }   
};

// Fungsi untuk login
const login = async (req, res) => {
    // Firebase Admin SDK tidak mendukung fungsi login
    // Anda perlu menggunakan Firebase Client SDK di sisi klien untuk login
    return res.status(400).send({ message: "Login functionality should be implemented on client side using Firebase Client SDK." });
};

module.exports = {
    register,
    login
};
