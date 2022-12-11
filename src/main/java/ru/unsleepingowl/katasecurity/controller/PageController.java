package ru.unsleepingowl.katasecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(value = "/user")
    public String getUserPage() {
        return "user";
    }

    @GetMapping(value = "/admin")
    public String getAdminPage() {
        return "admin";
    }
}
