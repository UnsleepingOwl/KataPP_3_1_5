package ru.unsleepingowl.katasecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingsController {

    @GetMapping(value= "/")
    public String printWelcome(ModelMap model) {
        List<String> strings = new ArrayList<>();
        strings.add("Welcome.");
        strings.add("This is Spring MVC application. Version 5.3.23 \n");
        model.addAttribute("messages", strings);
        return "start";
    }
}
