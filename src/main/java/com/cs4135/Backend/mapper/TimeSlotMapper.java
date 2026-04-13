package com.cs4135.Backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cs4135.Backend.dto.response.TimeSlotResponseDTO;
import com.cs4135.Backend.entity.Meeting;
import com.cs4135.Backend.entity.TimeSlot;
@Component
public class TimeSlotMapper {
    public TimeSlotResponseDTO toDto(TimeSlot entity) {
        Meeting meeting = entity.getMeeting();
        Long studentId = null;
        String studentName = null;
        String studentEmail = null;
        String note = null;
        if (meeting != null && meeting.getStudent() != null) {
            studentId = meeting.getStudent().getStudentId();
            studentName = meeting.getStudent().getName();
            studentEmail = meeting.getStudent().getEmail();
            note = meeting.getNotes();
        }
        return new TimeSlotResponseDTO(
            entity.getId(),
            entity.getAvailability().getId(),
            entity.getStartTime(),
            entity.getEndTime(),
            entity.isBooked(),
            studentId,
            studentName,
            studentEmail,
            note
        );
    }

    public List<TimeSlotResponseDTO> toDtoList(List<TimeSlot> entities) {
        if (entities.isEmpty()) return new ArrayList<>();
        return entities.stream().map(this::toDto).toList();
    }
}
