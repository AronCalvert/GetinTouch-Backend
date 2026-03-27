package com.cs4135.Backend.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StaffResponseDTO {
  private final Long id;
  private final String name;
  private final String email;
  private final String officeLocation;
  private final String title;
  private final String department;
}
