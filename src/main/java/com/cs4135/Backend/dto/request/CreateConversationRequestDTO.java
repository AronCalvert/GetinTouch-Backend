package com.cs4135.Backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateConversationRequestDTO {

    @NotNull
    private Long studentId;
}
