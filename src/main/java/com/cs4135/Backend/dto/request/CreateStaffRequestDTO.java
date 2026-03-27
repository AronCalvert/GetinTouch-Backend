package com.cs4135.Backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStaffRequestDTO extends CreateUserRequestDTO {
  private String department;
  private String title;
  private String officeLocation;
}
