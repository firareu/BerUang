const express = require('express');
const bodyParser = require('body-parser');
const admin = require('firebase-admin');

const serviceAccount = require('./config/app-test-beruang-firebase-adminsdk-5bkf7-46597f5aa6.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: 'https://app-test-beruang.firebaseio.com',
});

const app = express();
const PORT = process.env.PORT || 3000;

// Other configurations and middleware

// Routes and controllers
const userRoutes = require('./src/routes/userRoutes');
const incomeRoutes = require('./src/routes/incomeRoutes');
const outcomeRoutes = require('./src/routes/outcomeRoutes');

app.use(bodyParser.json());

// Routes
app.use('/users', userRoutes);
app.use('/incomes', incomeRoutes);
app.use('/outcomes', outcomeRoutes);

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

// console.log("")