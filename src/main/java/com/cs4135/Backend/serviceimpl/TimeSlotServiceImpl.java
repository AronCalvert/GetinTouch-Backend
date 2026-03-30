package com.cs4135.Backend.serviceimpl;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.entity.TimeSlot;

public class TimeSlotServiceImpl {
  public ArrayList<TimeSlot> generateTimeSlots(AvailabilityCreationRequestDTO dto) {
    ArrayList<TimeSlot> timeSlots = new ArrayList<>();
    LocalTime startTime = dto.getStartTime();
    LocalTime endTime = dto.getEndTime();
    LocalDate date = dto.getDate();
    Duration timeSlotLength = dto.getTimeSlotLength();

    Duration availabilityLength = Duration.between(startTime, endTime);
    int numOfTimeSlots = (int) availabilityLength.dividedBy(timeSlotLength);

    if (endTime.isAfter(startTime)) {
      for (curTime = startTime; curTime.isBefore(endTime); curTime += timeSlotLength) {

      }
    }
  }
}
