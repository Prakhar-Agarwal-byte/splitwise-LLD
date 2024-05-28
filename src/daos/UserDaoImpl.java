package daos;

import data.Db;
import exceptions.UserNotFoundException;
import models.User;

import java.util.*;

public class UserDaoImpl implements UserDao {
    private final Db db = Db.getINSTANCE();

    @Override
    public void addUser(User user) {
        db.users.put(user.getId(), user);
    }

    @Override
    public User getUser(UUID id) {
        User user = db.users.get(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(db.users.values());
    }

    @Override
    public void updateUser(User newUser) {
        User oldUser = db.users.get(newUser.getId());
        if (oldUser == null) {
            throw new UserNotFoundException();
        }
       db.users.put(oldUser.getId(), newUser);
    }

    @Override
    public void deleteUser(UUID id) {
        db.users.remove(id);
    }
}
