package com.messenger.messengerapp.entities;

import java.util.UUID;

import lombok.ToString;

@ToString
public class DTOMessage {
    private String message;
    private UUID sender;
    private UUID receiver;

    // Getters and Setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getSender() {
        return sender;
    }
    public void setSender(UUID sender) {
        this.sender = sender;
    }

    public UUID getReceiver() {
        return receiver;
    }
    public void setReceiver(UUID receiver) {
        this.receiver = receiver;
    }
}
