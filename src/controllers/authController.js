const { registerUser, 
        loginUser } = require("../models/auth");


// REGISTER
const register = async (req, res) => {
  try {
    const result = await registerUser(req.body);
    res.status(201).json(result);
  } catch (error) {
    console.error("Error in registration:", error);
    res.status(500).json({ error: "Internal Server Error" });
  }
};

// LOGIN
const login = async (req, res) => {
  try {
    const { email, password } = req.body;
    const userData = await loginUser(email, password);

    if (userData) {
      res.status(200).json(userData);
    } else {
      res.status(401).json({ error: "Invalid credentials" });
    }
  } catch (error) {
    console.error("Error in login:", error);
    res.status(500).json({ error: "Internal Server Error" });
  }
};

module.exports = { register, 
                   login };
