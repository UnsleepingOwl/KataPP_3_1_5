package ru.unsleepingowl.katasecurity.service;

import org.springframework.stereotype.Service;
import ru.unsleepingowl.katasecurity.DTO.RoleDTO;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.dao.RoleDao;
import ru.unsleepingowl.katasecurity.dao.UserDao;
import ru.unsleepingowl.katasecurity.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class DTOServiceImp implements DTOService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public DTOServiceImp(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User fromDTOToUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getAge(), userDTO.getRoles());
        user.setId(userDTO.getId());
        return user;
    }

    @Override
    public UserDTO userToDTO(String username) {
        return new UserDTO(userDao.findByUsername(username));
    }

    @Override
    public UserDTO userToDTO(User user) {
        return new UserDTO(user);
    }

    @Override
    public List<UserDTO> userListToDTOList() {
        return userDao.getUsersList().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public Set<RoleDTO> roleSetToDTOSet() {
        return roleDao.getRolesSet().stream().map(RoleDTO::new).collect(Collectors.toSet());
    }
}
