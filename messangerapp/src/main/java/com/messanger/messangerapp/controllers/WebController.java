package com.messanger.messangerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.messanger.messangerapp.entities.Message;
import com.messanger.messangerapp.services.MessageService;
import com.messanger.messangerapp.services.UserService;



@Controller
public class WebController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        System.out.println(authentication.getName());
        model.addAttribute("pageTitle", "Home");
        return "index";  // This must match src/main/resources/templates/index.html
    }

    @GetMapping("/messanger")
    public String messanger(Model model) {
        model.addAttribute("pageTitle", "messanger");
        model.addAttribute("users", userService.getAll());
        return "messanger";
    }
    
    
}
