package com.cs4135.Backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequestDTO extends CreateUserRequestDTO {
  private long studentId;
  private String course;
  private int year;
  private String department;
}
