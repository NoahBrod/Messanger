package com.messenger.messengerapp.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.messenger.messengerapp.entities.DTOMessage;
import com.messenger.messengerapp.entities.Message;

@Controller
public class MessageController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public void message(@Payload DTOMessage message) {
        // Message sentMessage = new Message(message.getMessage(), message.getSender(),
        // message.getReceiver());
        System.out.println(message.getReceiver().toString());
        messagingTemplate.convertAndSendToUser(message.getReceiver().toString(), "/queue/messages", message);
        
        // return sentMessage;
    }
}
