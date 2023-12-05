const express = require('express');
const bodyParser = require('body-parser');
const registerController = require('./controllers/registerController');
const loginController = require('./controllers/loginController');

const app = express();
const PORT = 3000;

app.use(bodyParser.json());

app.post('/register', registerController.registerUser);
app.post('/login', loginController.loginUser);

app.listen(PORT, () => {
  console.log(`Server berjalan di http://localhost:${PORT}`);
});
