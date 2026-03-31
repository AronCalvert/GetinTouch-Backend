package com.cs4135.Backend.mapper;

import com.cs4135.Backend.dto.response.MeetingResponseDTO;
import com.cs4135.Backend.entity.Meeting;
import org.springframework.stereotype.Component;

@Component
public class MeetingMapper {
    public MeetingResponseDTO toResponseDTO(Meeting meeting) {
        MeetingResponseDTO dto = new MeetingResponseDTO();

        dto.setId(meeting.getId());
        dto.setStatus(meeting.getStatus().toString());
        dto.setNotes(meeting.getNotes());
        dto.setCreatedAt(meeting.getCreatedAt());

        dto.setStaffId(meeting.getStaff().getId());
        dto.setStudentId(meeting.getStudent().getId());

        if (meeting.getTimeslot() != null) {
            dto.setTimeslotId(meeting.getTimeslot().getId());
            dto.setStartTime(meeting.getTimeslot().getStartTime());
            dto.setEndTime(meeting.getTimeslot().getEndTime());
        }

        return dto;
    }
}
