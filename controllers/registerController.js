// controllers/registerController.js
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const mysql = require('mysql2/promise');

const connection = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: 'password',
  database: 'auth',
});

async function registerUser(req, res) {
  try {
    const { username, password } = req.body;
    const hashedPassword = await bcrypt.hash(password, 10);
    await connection.execute('INSERT INTO users (username, password) VALUES (?, ?)', [username, hashedPassword]);
    res.status(201).send('Registrasi berhasil');
  } catch (error) {
    console.error('Error registering user:', error);
    res.status(500).send('Terjadi kesalahan saat registrasi');
  }
}

module.exports = {
  registerUser,
};
