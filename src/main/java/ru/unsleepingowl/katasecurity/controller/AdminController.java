package ru.unsleepingowl.katasecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.unsleepingowl.katasecurity.model.User;
import ru.unsleepingowl.katasecurity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getUsersList(Model model) {
        model.addAttribute("users_list", userService.getUsersList());
        return "/users/all";
    }

    @GetMapping("/admin/id={id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/id";
    }

    @GetMapping("/admin/new")
    public String newUserForm(@ModelAttribute("newUser") User user) {
        return "users/new";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/id={id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editedUser", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/admin/id={id}")
    public String updateUser(@ModelAttribute("editedUser") @Valid User user, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/id={id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
