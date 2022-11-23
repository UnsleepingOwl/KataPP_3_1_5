package ru.unsleepingowl.katasecurity.service;


import ru.unsleepingowl.katasecurity.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUser(Long id);
    void updateUser(User user, Long id);
    List<User> getUsersList();
    User getUserById(Long id);
}
