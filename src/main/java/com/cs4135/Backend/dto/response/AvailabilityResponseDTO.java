package com.cs4135.Backend.dto.response;

import java.sql.Date;
import java.time.LocalTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AvailabilityResponseDTO {
  private final long staffId;
  private final Date date;
  private final LocalTime startTime;
  private final LocalTime endTime;
}
