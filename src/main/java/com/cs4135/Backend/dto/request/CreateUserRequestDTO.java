package com.cs4135.Backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CreateUserRequestDTO {
  private String name;
  private String email;
  private String password;
}
