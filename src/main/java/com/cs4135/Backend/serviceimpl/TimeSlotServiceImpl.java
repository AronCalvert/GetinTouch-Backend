package com.cs4135.Backend.serviceimpl;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.stereotype.Service;

// In progress

import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.TimeSlotResponseDTO;
import com.cs4135.Backend.entity.Availability;
import com.cs4135.Backend.entity.TimeSlot;
import com.cs4135.Backend.mapper.TimeSlotMapper;
import com.cs4135.Backend.repository.TimeSlotRepository;
import com.cs4135.Backend.service.TimeSlotService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService{
  private final TimeSlotRepository timeSlotRepository;
  private final TimeSlotMapper timeSlotMapper;
  
  public ArrayList<TimeSlot> generateTimeSlotsForWindow(AvailabilityCreationRequestDTO dto,Availability availability, LocalDate date) {
    ArrayList<TimeSlot> timeSlots = new ArrayList<>();//Creates array list of slots
    LocalTime startTime = availability.getStartTime();
    LocalTime endTime = availability.getEndTime();
    DayOfWeek day = availability.getDay();
    Duration timeSlotLength = dto.getTimeSlotLength(); // uses dto to get timeslotlength

    Duration availabilityLength = Duration.between(startTime, endTime);//get differnce between start and end
    int numOfTimeSlots = (int) availabilityLength.dividedBy(timeSlotLength);//gets amount of timeslots

    if (endTime.isAfter(startTime)) {//if end time takes place after starttime
      for (LocalTime curTime = startTime; curTime.isBefore(endTime); curTime = curTime.plus(timeSlotLength)) {//for as long as start time is before end time(increments by timeslot length so lets say 30 mins)
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
    List<TimeSlot> entities = timeSlotRepository.findByAvailabilityByStaffId(staffId);
    return timeSlotMapper.toDtoList(entities);
  }
  @Transactional
  public TimeSlotResponseDTO markSlotBooked(long slotId) {
    TimeSlot slot = timeSlotRepository.findById(slotId).
    orElseThrow(() -> new RuntimeException("TimeSlot not found!"));
    slot.setBooked(true);
    TimeSlot saved = timeSlotRepository.save(slot);
    return timeSlotMapper.toDto(saved);
  }

  @Transactional
  public TimeSlotResponseDTO markSlotFree(long slotId) {
    TimeSlot slot = timeSlotRepository.findById(slotId).
    orElseThrow(() -> new RuntimeException("TimeSlot not Found!"));
    slot.setBooked(false);
    TimeSlot saved = timeSlotRepository.save(slot);
    return timeSlotMapper.toDto(saved);
  }

}
