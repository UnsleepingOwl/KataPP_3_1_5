package ru.unsleepingowl.katasecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unsleepingowl.katasecurity.DTO.RoleDTO;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class RESTAdminController {

    private final UserService userService;

    public RESTAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getUsersList() {
        return new ResponseEntity<>(userService.convertUserListToDTOList(), HttpStatus.OK);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new UserDTO(userService.getUserById(id)), HttpStatus.FOUND);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Set<RoleDTO>> getAllRolesSet() {
        return new ResponseEntity<>(userService.convertRoleSetToDTOSet(), HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.addUser(userService.convertDTOToUser(userDTO));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/edit")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(userService.convertDTOToUser(userDTO), userDTO.getId());
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
