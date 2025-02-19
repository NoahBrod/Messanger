package com.messanger.messangerapp.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messanger.messangerapp.entities.Message;
import com.messanger.messangerapp.services.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "/api/v1/messages")
public class MessageRest {
    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public List<Message> messages() {
        return messageService.getAll();
    }

    @PostMapping("/new")
    public String newMessage(Message message) {
        System.out.println(message.toString());
        return "RECIEVED";
    }
    
    
}
