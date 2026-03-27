package com.cs4135.Backend.dto.response;

import lombok.Getter;

@Getter
public class StaffResponseDTO {
  private Long id;
  private String name;
  private String email;
  private String officeLocation;
  private String title;
  private String department;

  public StaffResponseDTO(
      Long id,
      String name,
      String email,
      String officeLocation,
      String title,
      String department) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.officeLocation = officeLocation;
    this.title = title;
    this.department = department;
  }
}
