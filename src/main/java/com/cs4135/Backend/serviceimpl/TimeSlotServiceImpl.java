package com.cs4135.Backend.serviceimpl;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

// In progress

import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.entity.TimeSlot;

public class TimeSlotServiceImpl {
  public ArrayList<TimeSlot> generateTimeSlotsForWindow(AvailabilityCreationRequestDTO dto) {
    ArrayList<TimeSlot> timeSlots = new ArrayList<>();
    LocalTime startTime = dto.getStartTime();
    LocalTime endTime = dto.getEndTime();
    DayOfWeek day = dto.getDay();
    Duration timeSlotLength = dto.getTimeSlotLength();

    Duration availabilityLength = Duration.between(startTime, endTime);
    int numOfTimeSlots = (int) availabilityLength.dividedBy(timeSlotLength);

    if (endTime.isAfter(startTime)) {
      for (LocalTime curTime = startTime; curTime.isBefore(endTime); curTime = curTime.plus(timeSlotLength)) {

      }
    }

    return null;
  }
}
