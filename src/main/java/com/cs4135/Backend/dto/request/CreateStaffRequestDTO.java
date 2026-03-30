package com.cs4135.Backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStaffRequestDTO extends CreateUserRequestDTO {

  @NotNull
  private String department;

  private String title;

  private String officeLocation;
}
