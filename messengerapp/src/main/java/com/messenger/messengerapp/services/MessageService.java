package com.messenger.messengerapp.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.messengerapp.entities.Message;
import com.messenger.messengerapp.repositories.MessageRepo;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public List<Message> getAll() {
        return messageRepo.findAll();
    }

    public List<Message> getChat(UUID sender, UUID receiver) {
        return messageRepo.getChat(sender, receiver);
    }

    public void save(Message sentMessage) {
        messageRepo.save(sentMessage);
    }
    
}
