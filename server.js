// server.js
const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const authRoutes = require('./src/routes/authRoutes');

// Inisialisasi Firebase
const admin = require('./src/config/firebaseConfig');

// Inisialisasi Express
const app = express();

// Menggunakan middleware
app.use(cors());
app.use(bodyParser.json());

// Mengatur rute
app.use('/api/auth', authRoutes);

// Mendefinisikan port
const port = process.env.PORT || 3000;

// Menjalankan server
app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
    
});
