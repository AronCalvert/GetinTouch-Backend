package com.cs4135.Backend.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminResponseDTO {
  private final long id;
  private final String email;
  private final String name;
  private final LocalDateTime createdAt;
}
