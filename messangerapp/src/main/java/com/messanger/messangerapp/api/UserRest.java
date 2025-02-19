package com.messanger.messangerapp.api;

import org.springframework.web.bind.annotation.RestController;

import com.messanger.messangerapp.entities.User;
import com.messanger.messangerapp.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/api/v1/users")
public class UserRest {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> users() {
        return userService.getAll();
    }
    
}
