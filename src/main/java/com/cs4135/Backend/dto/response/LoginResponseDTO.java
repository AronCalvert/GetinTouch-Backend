package com.cs4135.Backend.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponseDTO {
  private final String accessToken;
  private String tokenType = "Bearer";
}
