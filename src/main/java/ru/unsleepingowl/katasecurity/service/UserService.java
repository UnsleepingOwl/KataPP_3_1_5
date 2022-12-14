package ru.unsleepingowl.katasecurity.service;

import ru.unsleepingowl.katasecurity.DTO.RoleDTO;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user, Long id);

    List<User> getUsersList();

    User getUserById(Long id);

    User findByUsername(String username);

    User convertDTOToUser(UserDTO userDTO);

    List<UserDTO> convertUserListToDTOList();

    Set<RoleDTO> convertRoleSetToDTOSet();

}
