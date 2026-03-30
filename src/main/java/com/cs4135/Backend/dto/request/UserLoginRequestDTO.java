package com.cs4135.Backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserLoginRequestDTO {

  @Email
  private String email;

  @Size(min = 10, max = 80)
  private String password;
}
