package com.cs4135.Backend.dto.request;

import lombok.Getter;

@Getter
public class UserLoginRequestDTO {
  private String email;
  private String password;
}
