package com.cs4135.Backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMeetingRequestDTO {
    @NotNull(message = "Timeslot ID is required to book a meeting")
    private Long timeslotId;

    private String notes;
}
