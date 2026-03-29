package com.cs4135.Backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CreateUserRequestDTO {

  @NotNull
  @Size(min = 2, max = 30)
  private String name;

  @NotNull
  @Email
  private String email;

  @Size(min = 10, max = 80)
  private String password;
}
