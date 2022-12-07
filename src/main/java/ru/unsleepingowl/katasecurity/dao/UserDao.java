package ru.unsleepingowl.katasecurity.dao;

import ru.unsleepingowl.katasecurity.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void addUser(User user, Set<String> rolesStringSet);
    void deleteUser(Long id);
    void updateUser(User user, Long id);
    List<User> getUsersList();
    User getUserById(Long id);
    User findByUsername(String username);
    User createUser();
}
