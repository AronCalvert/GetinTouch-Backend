package com.cs4135.Backend.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudentResponseDTO {
  private final Long id;
  private final String name;
  private final String email;
  private final long studentId;
  private final String course;
  private final int year;
  private final String department;
}
