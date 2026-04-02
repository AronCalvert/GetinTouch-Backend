package com.cs4135.Backend.dto.request;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailabilityCreationRequestDTO {
  private long staffId;
  private DayOfWeek day;
  private LocalTime startTime;
  private LocalTime endTime;
  private LocalDate endDate;
  private Duration timeSlotLength;
}
