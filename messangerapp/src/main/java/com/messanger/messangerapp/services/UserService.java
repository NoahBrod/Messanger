package com.messanger.messangerapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.messanger.messangerapp.entities.User;
import com.messanger.messangerapp.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    public void registerUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

}
