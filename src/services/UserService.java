package services;

import daos.UserDao;
import daos.UserDaoImpl;
import models.User;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDao userDao = new UserDaoImpl();

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User getUserById(UUID id) {
        return userDao.getUser(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(UUID id) {
        userDao.deleteUser(id);
    }
}
