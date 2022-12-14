package ru.unsleepingowl.katasecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unsleepingowl.katasecurity.DTO.UserDTO;
import ru.unsleepingowl.katasecurity.service.DTOService;

import java.security.Principal;


@RestController
@RequestMapping("/api/user")
public class RESTUserController {

    private final DTOService serviceDTO;

    public RESTUserController(DTOService serviceDTO) {
        this.serviceDTO = serviceDTO;
    }

    @GetMapping(value = "")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(serviceDTO.userToDTO(principal.getName()), HttpStatus.OK);
    }
}
