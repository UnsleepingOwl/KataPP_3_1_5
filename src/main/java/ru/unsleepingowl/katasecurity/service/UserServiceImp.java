package ru.unsleepingowl.katasecurity.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unsleepingowl.katasecurity.DTO.RoleDTO;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.dao.RoleDao;
import ru.unsleepingowl.katasecurity.dao.UserDao;
import ru.unsleepingowl.katasecurity.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImp(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user, Long id) {
        userDao.updateUser(user, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersList() {
        return userDao.getUsersList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }


    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User convertDTOToUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getAge(), userDTO.getRoles());
        user.setId(userDTO.getId());
        return user;
    }

    @Override
    public List<UserDTO> convertUserListToDTOList() {
        return getUsersList().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public Set<RoleDTO> convertRoleSetToDTOSet() {
        return roleDao.getRolesSet().stream().map(RoleDTO::new).collect(Collectors.toSet());
    }
}
