package com.cs4135.Backend.dto.request;

import java.time.LocalTime;
import java.util.Date;

import com.cs4135.Backend.entity.Staff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailabilityCreationRequestDTO {
  private long staffId;
  private Date date;
  private LocalTime startTime;
  private LocalTime endTime;
}
