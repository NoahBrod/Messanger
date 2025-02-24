package com.messenger.messengerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.messenger.messengerapp.entities.Message;
import com.messenger.messengerapp.services.MessageService;
import com.messenger.messengerapp.services.UserService;



@Controller
public class WebController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("user", authentication.getName());
        }
        model.addAttribute("pageTitle", "Home");
        return "index";  // This must match src/main/resources/templates/index.html
    }

    @GetMapping("/messenger")
    public String messenger(Model model, Authentication authentication) {
        model.addAttribute("sender", userService.getUserByUsername(authentication.getName()).getId());
        model.addAttribute("pageTitle", "messenger");
        model.addAttribute("users", userService.getAll());
        return "messenger";
    }
    
    
}
