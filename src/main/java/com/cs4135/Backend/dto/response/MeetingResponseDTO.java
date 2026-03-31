package com.cs4135.Backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeetingResponseDTO {
    private Long id;
    private Long staffId;
    private Long studentId;
    private Long timeslotId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String status;
    private String notes;
    private LocalDateTime createdAt;
}
