package com.cs4135.Backend.mapper;

import com.cs4135.Backend.dto.response.ConversationResponseDTO;
import com.cs4135.Backend.entity.Conversation;
import org.springframework.stereotype.Component;

@Component
public class ConversationMapper {

    public ConversationResponseDTO toResponseDTO(Conversation conversation) {
        ConversationResponseDTO dto = new ConversationResponseDTO();

        dto.setId(conversation.getId());
        dto.setStaffId(conversation.getStaff().getId());
        dto.setStaffName(conversation.getStaff().getName());
        dto.setStaffEmail(conversation.getStaff().getEmail());
        dto.setStudentId(conversation.getStudent().getId());
        dto.setStudentName(conversation.getStudent().getName());
        dto.setStudentEmail(conversation.getStudent().getEmail());

        return dto;
    }
}
