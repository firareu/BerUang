const register = async (req, res) => {
  const { email, 
          password, 
          name, 
          dob, 
          profilePicture, 
          premiumStatus, 
          gender } = req.body;

  try {
    const userId = await createUserModel({
      email,
      password,
      name,
      dob,
      profilePicture,
      premiumStatus,
      gender,
    });
    res.status(201).json({ userId });
  } catch (error) {
    console.error('Error creating user:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

const login = async (req, res) => {
  const { email, password } = req.body;

  try {
    const userRecord = await admin.auth().getUserByEmail(email);

    if (password === userRecord.password) {
      res.status(200).json({ userId: userRecord.uid });
    } else {
      res.status(401).json({ error: 'Invalid credentials' });
    }
  } catch (error) {
    console.error('Error logging in:', error);
    res.status(401).json({ error: 'Invalid credentials' });
  }
};

module.exports = { register, 
                   login };
