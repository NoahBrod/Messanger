package com.messanger.messangerapp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messanger.messangerapp.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
