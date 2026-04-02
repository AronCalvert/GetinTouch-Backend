package com.cs4135.Backend.dto.response;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.cs4135.Backend.entity.Availability;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TimeSlotResponseDTO {
    private final long availabilityId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final boolean isBooked;
}
