package com.messanger.messangerapp.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String message;
    private UUID senderId;
    private UUID recieverId;

    public Message(String message, UUID senderId, UUID reciverId) {
        this.message = message;
        this.senderId = senderId;
        this.recieverId = reciverId;
    }

    public Message(String message) {
        this.message = message;
    }
}
