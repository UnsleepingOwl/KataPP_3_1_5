package ru.unsleepingowl.katasecurity.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.unsleepingowl.katasecurity.model.User;

import java.util.List;

@Service
public interface UserService {
//    void addUser(User user);
//
//    void deleteUser(Long id);
//
//    void updateUser(User user, Long id);
//
//    List<User> getUsersList();
//
//    User getUserById(Long id);

    User findByUsername(String username);
}
