package com.cs4135.Backend.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TimeSlotResponseDTO {
  private final long id;
  private final long availabilityId;
  private final LocalDateTime startTime;
  private final LocalDateTime endTime;
  private final boolean isBooked;
}
