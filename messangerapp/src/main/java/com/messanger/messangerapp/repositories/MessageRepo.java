package com.messanger.messangerapp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messanger.messangerapp.entities.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, UUID> {
    
}
