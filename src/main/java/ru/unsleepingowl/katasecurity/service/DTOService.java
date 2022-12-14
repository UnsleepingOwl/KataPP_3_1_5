package ru.unsleepingowl.katasecurity.service;

import ru.unsleepingowl.katasecurity.DTO.RoleDTO;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.model.User;

import java.util.List;
import java.util.Set;

public interface DTOService {

    User fromDTOToUser(UserDTO userDTO);

    UserDTO userToDTO(String username);

    UserDTO userToDTO(User user);

    List<UserDTO> userListToDTOList();

    Set<RoleDTO> roleSetToDTOSet();
}
