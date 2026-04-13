package com.cs4135.Backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequestDTO {

    @NotBlank
    private String content;
}
