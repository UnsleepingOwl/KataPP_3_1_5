package ru.unsleepingowl.katasecurity.dao;


import ru.unsleepingowl.katasecurity.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(Long id);
    void updateUser(User user, Long id);
    List<User> getUsersList();
    User getUserById(Long id);
}
