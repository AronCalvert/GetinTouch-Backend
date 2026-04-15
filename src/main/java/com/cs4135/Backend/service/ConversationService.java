package com.cs4135.Backend.service;

import com.cs4135.Backend.dto.response.ConversationResponseDTO;
import com.cs4135.Backend.dto.response.MessageResponseDTO;

import java.util.List;

public interface ConversationService {
    ConversationResponseDTO createOrGetConversation(Long studentId, String staffEmail);
    List<ConversationResponseDTO> getConversationsForUser(String email);
    List<MessageResponseDTO> getMessages(Long conversationId);
    MessageResponseDTO sendMessage(Long conversationId, String content, String senderEmail);
    MessageResponseDTO sendNotification(Long staffId, String studentEmail, String content);
}
