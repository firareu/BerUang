// controllers/loginController.js
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const mysql = require('mysql2/promise');

const connection = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: 'password',
  database: 'auth',
});

async function loginUser(req, res) {
  try {
    const { username, password } = req.body;
    const [rows] = await connection.execute('SELECT * FROM users WHERE username = ?', [username]);
    const user = rows[0];

    if (user && (await bcrypt.compare(password, user.password))) {
      const token = jwt.sign({ username }, 'secret_key');
      res.json({ token });
    } else {
      res.status(401).send('Username atau password salah');
    }
  } catch (error) {
    console.error('Error logging in user:', error);
    res.status(500).send('Terjadi kesalahan saat login');
  }
}

module.exports = {
  loginUser,
};
