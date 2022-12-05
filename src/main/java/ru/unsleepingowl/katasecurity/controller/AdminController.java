package ru.unsleepingowl.katasecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.unsleepingowl.katasecurity.model.User;
import ru.unsleepingowl.katasecurity.service.RoleService;
import ru.unsleepingowl.katasecurity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String getUsersList(Model model, Principal principal) {
        model.addAttribute("current_user", userService.findByUsername(principal.getName()));
        model.addAttribute("users_list", userService.getUsersList());
        model.addAttribute("new_user", userService.createUser());
        model.addAttribute("roles_set", roleService.getRolesSet());
        return "admin";
    }

    @GetMapping(value = "/id={id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/id";
    }

    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("new_user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/id={id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("edited_user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping(value = "/id={id}")
    public String updateUser(@ModelAttribute("edited_user") @Valid User user, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/id={id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
