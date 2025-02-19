package com.messanger.messangerapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messanger.messangerapp.entities.Message;
import com.messanger.messangerapp.repositories.MessageRepo;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public List<Message> getAll() {
        return messageRepo.findAll();
    }
    
}
