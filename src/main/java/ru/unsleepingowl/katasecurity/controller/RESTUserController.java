package ru.unsleepingowl.katasecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.service.UserService;

import java.security.Principal;


@RestController
@RequestMapping("/api/user")
public class RESTUserController {

    private final UserService userService;

    public RESTUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(new UserDTO(userService.findByUsername(principal.getName())), HttpStatus.OK);
    }

}
