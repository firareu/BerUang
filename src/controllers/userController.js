const { createUser: createUserModel,
        getUser: getUserModel,
        getUserById: getUserByIdModel, 
        updateUser: updateUserModel, 
        deleteUser: deleteUserModel } = require('../models/user');
        
// POST
const createUser = async (req, res) => {
  try {
    console.log('Received data:', req.body);
    const userId = await createUserModel(req.body);
    console.log('User created successfully:', userId);
    res.status(201).json({ userId });
  } catch (error) {
    console.error('Error creating user:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

// GET ALL
const getUser = async (req, res) => {
  try {
    const user = await getUserModel();
    if (user.length > 0) {
      res.status(200).json({ user });
    } else {
      res.status(404).json({ error: 'User not found' });
    }
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

// GET
const getUserById = async (req, res) => {
  try {
    const userId = req.params.userId;
    if (!userId) {
      return res.status(400).json({ error: 'User ID is missing in the request' });
    }

    const user = await getUserByIdModel(userId);
    console.log("this is user", user)
    if (user) {
      res.status(200).json({ user });
    } else {
      res.status(404).json({ error: 'User not found' });
    }
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

// PUT
const updateUser = async (req, res) => {
  try {
    const userId = req.params.userId;
    await updateUserModel(userId, req.body);
    res.status(200).json({ message: 'User updated successfully' });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

// DELETE
const deleteUser = async (req, res) => {
  try {
    const userId = req.params.userId;
    await deleteUserModel(userId);
    res.status(200).json({ message: 'User deleted successfully' });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};

module.exports = { createUser, 
                   getUser, 
                   getUserById, 
                   updateUser, 
                   deleteUser };
