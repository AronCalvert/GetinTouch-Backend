package com.cs4135.Backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequestDTO extends CreateUserRequestDTO {

  @NotNull
  @Size(min = 8, max = 8)
  private long studentId;

  @NotNull
  private String course;

  @Size(min = 1, max = 5)
  private int year;

  @NotNull
  private String department;
}
