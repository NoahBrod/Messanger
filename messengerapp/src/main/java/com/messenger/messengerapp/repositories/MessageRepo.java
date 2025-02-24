package com.messenger.messengerapp.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.messenger.messengerapp.entities.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM message WHERE (sender_id = ?1 AND receiver_id = ?2) OR (sender_id = ?2 AND receiver_id = ?1)", nativeQuery = true)
    List<Message> getChat(UUID sender, UUID receiver);
}
