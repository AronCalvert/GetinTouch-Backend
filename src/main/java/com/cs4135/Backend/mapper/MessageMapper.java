package com.cs4135.Backend.mapper;

import com.cs4135.Backend.dto.response.MessageResponseDTO;
import com.cs4135.Backend.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageResponseDTO toResponseDTO(Message message) {
        MessageResponseDTO dto = new MessageResponseDTO();

        dto.setId(message.getId());
        dto.setConversationId(message.getConversation().getId());
        dto.setSenderId(message.getSender().getId());
        dto.setSenderName(message.getSender().getName());
        dto.setSenderEmail(message.getSender().getEmail());
        dto.setContent(message.getContent());
        dto.setSentAt(message.getSentAt());

        return dto;
    }
}
