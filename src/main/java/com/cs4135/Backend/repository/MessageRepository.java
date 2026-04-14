package com.cs4135.Backend.repository;

import com.cs4135.Backend.entity.Conversation;
import com.cs4135.Backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationOrderBySentAtAsc(Conversation conversation);
}
