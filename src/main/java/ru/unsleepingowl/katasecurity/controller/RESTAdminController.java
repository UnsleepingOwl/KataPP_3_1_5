package ru.unsleepingowl.katasecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.model.User;
import ru.unsleepingowl.katasecurity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class RESTAdminController {

    private final UserService userService;

    public RESTAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(new UserDTO(userService.findByUsername(principal.getName())), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getUsersList() {
        return new ResponseEntity<>(convertUserListToDTOList(), HttpStatus.OK);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new UserDTO(userService.getUserById(id)), HttpStatus.FOUND);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.addUser(convertDTOToUser(userDTO));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(convertDTOToUser(userDTO), userDTO.getId());
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User convertDTOToUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getAge(), userDTO.getRoles());
        user.setId(userDTO.getId());
        return user;
    }

    private List<UserDTO> convertUserListToDTOList() {
        return userService.getUsersList().stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
