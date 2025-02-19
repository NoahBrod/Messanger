package com.messanger.messangerapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class WebController {
    @Value("${SQL_PASS}")
    private String sqlPass;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        System.out.println(authentication.getName());
        model.addAttribute("pageTitle", "Home");
        return "index";  // This must match src/main/resources/templates/index.html
    }

    @GetMapping("/messanger")
    public String messanger(Model model) {
        model.addAttribute("pageTitle", "messanger");
        return "messanger";
    }
    
    
}
