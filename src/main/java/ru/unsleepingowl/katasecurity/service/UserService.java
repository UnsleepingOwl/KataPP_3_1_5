package ru.unsleepingowl.katasecurity.service;

import ru.unsleepingowl.katasecurity.model.User;

public interface UserService {

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user, Long id);

    User getUserById(Long id);

    User findByUsername(String username);

}
