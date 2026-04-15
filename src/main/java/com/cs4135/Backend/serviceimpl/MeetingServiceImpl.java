package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.dto.request.BookMeetingRequestDTO;
import com.cs4135.Backend.dto.response.MeetingResponseDTO;
import com.cs4135.Backend.entity.Meeting;
import com.cs4135.Backend.entity.Student;
import com.cs4135.Backend.entity.TimeSlot;
import com.cs4135.Backend.enums.Status;
import com.cs4135.Backend.mapper.MeetingMapper;
import com.cs4135.Backend.repository.MeetingRepository;
import com.cs4135.Backend.repository.StudentRepository;
import com.cs4135.Backend.repository.TimeSlotRepository;
import com.cs4135.Backend.service.MeetingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final StudentRepository studentRepository;
    private final MeetingMapper meetingMapper;

    @Override
    @Transactional(timeout = 5)
    public MeetingResponseDTO bookMeeting(BookMeetingRequestDTO dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        TimeSlot slot = timeSlotRepository.findById(dto.getTimeslotId())
                .orElseThrow(() -> new EntityNotFoundException("Timeslot not found"));

        if (slot.isBooked()) {
            throw new IllegalStateException("Timeslot already taken");
        }

        Meeting meeting = new Meeting();
        meeting.setStaff(slot.getAvailability().getStaff());
        meeting.setStudent(student);
        meeting.setTimeslot(slot);
        meeting.setStatus(Status.SCHEDULED);
        meeting.setNotes(dto.getNotes());

        slot.setBooked(true);
        timeSlotRepository.save(slot);

        Meeting savedMeeting = meetingRepository.save(meeting);

        return meetingMapper.toResponseDTO(savedMeeting);
    }

    @Override
    @Transactional
    public MeetingResponseDTO getMeetingById(long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meeting not found with ID: " + id));

        return meetingMapper.toResponseDTO(meeting);
    }

    @Override
    @Transactional(timeout = 5)
    public MeetingResponseDTO updateMeetingStatus(long id, String status) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meeting not found with ID: " + id));

        try{
            meeting.setStatus(Status.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        Meeting savedMeeting = meetingRepository.save(meeting);
        return meetingMapper.toResponseDTO(savedMeeting);
    }

    @Override
    @Transactional
    public List<MeetingResponseDTO> getMeetingsForStudent(long studentId) {
        List<Meeting> meetings = meetingRepository.findByStudentId(studentId);

        return meetings.stream()
                .map(meetingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MeetingResponseDTO> getMeetingsForStaff(long staffId) {
        List<Meeting> meetings = meetingRepository.findByStaffId(staffId);

        return meetings.stream()
                .map(meetingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
