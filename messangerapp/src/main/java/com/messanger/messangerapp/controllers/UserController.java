package com.messanger.messangerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.messanger.messangerapp.entities.User;
import com.messanger.messangerapp.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("pageTitle", "Log In");
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password.");
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("user", new User());
        return "auth/register";
    }
    
    @PostMapping("/register")
    public String registerSave(User user) {
        try {
            userService.registerUser(user);
            return "redirect:/";
        } catch (Exception e) {
            // TODO: handle exception
        }

        return "auth/register";
    }
    
    
}
