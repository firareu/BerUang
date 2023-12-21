const express = require("express");
const bodyParser = require("body-parser");
const admin = require("firebase-admin");

const serviceAccount = require("./config/serviceAccountKey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://app-beruang-CH2-PS111.firebaseio.com",
});

const app = express();
const PORT = process.env.PORT || 3000;

// Routes and controllers
const authRoutes = require("./src/routes/authRoutes");
const userRoutes = require("./src/routes/userRoutes");
const incomeRoutes = require("./src/routes/incomeRoutes");
const outcomeRoutes = require('./src/routes/outcomeRoutes');
const allocationRoutes = require('./src/routes/allocationRoutes');

app.use(bodyParser.json());
// app.use(express.json());

// Routes
app.use('/auth', authRoutes);
app.use('/users', userRoutes);
app.use('/incomes', incomeRoutes);
app.use('/outcomes', outcomeRoutes);
app.use('/allocations', allocationRoutes);

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});