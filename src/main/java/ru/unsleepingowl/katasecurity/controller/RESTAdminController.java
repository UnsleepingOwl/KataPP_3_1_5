package ru.unsleepingowl.katasecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unsleepingowl.katasecurity.DTO.RoleDTO;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.service.DTOService;
import ru.unsleepingowl.katasecurity.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class RESTAdminController {

    private final UserService userService;
    private final DTOService serviceDTO;

    public RESTAdminController(UserService userService, DTOService serviceDTO) {
        this.userService = userService;
        this.serviceDTO = serviceDTO;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getUsersList() {
        return new ResponseEntity<>(serviceDTO.userListToDTOList(), HttpStatus.OK);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(serviceDTO.userToDTO(userService.getUserById(id)), HttpStatus.FOUND);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Set<RoleDTO>> getAllRolesSet() {
        return new ResponseEntity<>(serviceDTO.roleSetToDTOSet(), HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.addUser(serviceDTO.fromDTOToUser(userDTO));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/edit")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(serviceDTO.fromDTOToUser(userDTO), userDTO.getId());
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
