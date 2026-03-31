package com.cs4135.Backend.service;

import com.cs4135.Backend.dto.request.BookMeetingRequestDTO;
import com.cs4135.Backend.dto.response.MeetingResponseDTO;

import java.util.List;

public interface MeetingService {
    MeetingResponseDTO bookMeeting(BookMeetingRequestDTO dto);

    MeetingResponseDTO getMeetingById(long id);

    MeetingResponseDTO updateMeetingStatus(long id, String status);

    List<MeetingResponseDTO> getMeetingsForStudent(long studentId);

    List<MeetingResponseDTO> getMeetingsForStaff(long staffId);
}
