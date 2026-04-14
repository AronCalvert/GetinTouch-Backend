package com.cs4135.Backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationResponseDTO {
    private Long id;
    private Long staffId;
    private String staffName;
    private String staffEmail;
    private Long studentId;
    private String studentName;
    private String studentEmail;
}
