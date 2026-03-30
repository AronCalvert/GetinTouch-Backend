package com.cs4135.Backend.dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AvailabilityResponseDTO {
  private final long staffId;
  private final DayOfWeek day;
  private final LocalTime startTime;
  private final LocalTime endTime;
}
