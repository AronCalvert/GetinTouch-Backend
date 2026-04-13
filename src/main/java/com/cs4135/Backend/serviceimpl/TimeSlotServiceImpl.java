package com.cs4135.Backend.serviceimpl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

// In progress

import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.TimeSlotResponseDTO;
import com.cs4135.Backend.entity.Availability;
import com.cs4135.Backend.entity.Meeting;
import com.cs4135.Backend.entity.Student;
import com.cs4135.Backend.entity.TimeSlot;
import com.cs4135.Backend.enums.Status;
import com.cs4135.Backend.mapper.TimeSlotMapper;
import com.cs4135.Backend.repository.MeetingRepository;
import com.cs4135.Backend.repository.StudentRepository;
import com.cs4135.Backend.repository.TimeSlotRepository;
import com.cs4135.Backend.service.TimeSlotService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {
  private final TimeSlotRepository timeSlotRepository;
  private final TimeSlotMapper timeSlotMapper;
  private final MeetingRepository meetingRepository;
  private final StudentRepository studentRepository;

  public ArrayList<TimeSlot> generateTimeSlotsForWindow(AvailabilityCreationRequestDTO dto, Availability availability,
      LocalDate date) {
    ArrayList<TimeSlot> timeSlots = new ArrayList<>();// Creates array list of slots
    LocalTime startTime = availability.getStartTime();
    LocalTime endTime = availability.getEndTime();
    Duration timeSlotLength = dto.getTimeSlotLength(); // uses dto to get timeslotlength

    // Iterate from startTime to endTime, incrementing by timeslot length (e.g. 30 mins)
    if (endTime.isAfter(startTime)) {
      for (LocalTime curTime = startTime; curTime.isBefore(endTime); curTime = curTime.plus(timeSlotLength)) {
        TimeSlot slot = new TimeSlot();
        slot.setAvailability(availability);
        slot.setStartTime(LocalDateTime.of(date, curTime));
        slot.setEndTime(LocalDateTime.of(date, curTime.plus(timeSlotLength)));
        slot.setBooked(false);
        timeSlots.add(slot);
        timeSlotRepository.save(slot);
      }
    }
    return timeSlots;
  }

  public List<TimeSlotResponseDTO> getAvailableTimeslots(long staffId) {
    List<TimeSlot> entities = timeSlotRepository.findUnbookedByStaffId(staffId);
    return timeSlotMapper.toDtoList(entities);
  }

  @Transactional
  public List<TimeSlotResponseDTO> getAllTimeslots(long staffId) {
    List<TimeSlot> entities = timeSlotRepository.findAllByStaffId(staffId);
    return timeSlotMapper.toDtoList(entities);
  }

  @Transactional
  public TimeSlotResponseDTO markSlotBooked(long slotId, String note) {
    TimeSlot slot = timeSlotRepository.findById(slotId).orElseThrow(() -> new EntityNotFoundException("TimeSlot not found!"));

    if (slot.isBooked()) {
      throw new IllegalStateException("Timeslot already booked");
    }

    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Student student = studentRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Student not found"));

    Meeting meeting = new Meeting();
    meeting.setStaff(slot.getAvailability().getStaff());
    meeting.setStudent(student);
    meeting.setTimeslot(slot);
    meeting.setStatus(Status.SCHEDULED);
    meeting.setNotes(note);
    meetingRepository.save(meeting);

    slot.setBooked(true);
    TimeSlot saved = timeSlotRepository.save(slot);
    return timeSlotMapper.toDto(saved);
  }

  @Transactional
  public TimeSlotResponseDTO markSlotFree(long slotId) {
    TimeSlot slot = timeSlotRepository.findById(slotId).orElseThrow(() -> new RuntimeException("TimeSlot not Found!"));
    slot.setBooked(false);
    TimeSlot saved = timeSlotRepository.save(slot);
    return timeSlotMapper.toDto(saved);
  }

}
