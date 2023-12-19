const express = require('express');
const bodyParser = require('body-parser');
const admin = require('firebase-admin');

const serviceAccount = require('./config/serviceAccountKey.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: 'https://app-beruang-CH2-PS111.firebaseio.com',
});

const app = express();
const PORT = process.env.PORT || 3000;

app.post('/register', async (req, res) => {
  try {
    const { email, password } = req.body;

    // Create user with email and password
    const userRecord = await admin.auth().createUser({
      email,
      password,
    });

    res.status(200).json({ uid: userRecord.uid });
  } catch (error) {
    console.error('Error creating user:', error.message);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

app.post('/login', async (req, res) => {
  try {
    const { email, password } = req.body;

    // Sign in user with email and password
    const userRecord = await admin.auth().getUserByEmail(email);
    await admin.auth().signInWithEmailAndPassword(email, password);

    res.status(200).json({ uid: userRecord.uid });
  } catch (error) {
    console.error('Error signing in:', error.message);
    res.status(401).json({ error: 'Invalid credentials' });
  }
});

// Routes and controllers
const userRoutes = require('./src/routes/userRoutes');
const incomeRoutes = require('./src/routes/incomeRoutes');
// const outcomeRoutes = require('./src/routes/outcomeRoutes');

app.use(bodyParser.json());

// Routes
// app.use('/api/auth', authRoutes);
app.use('/users', userRoutes);
app.use('/incomes', incomeRoutes);
// app.use('/outcomes', outcomeRoutes);

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
