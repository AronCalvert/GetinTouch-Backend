package com.cs4135.Backend.service;

import com.cs4135.Backend.dto.request.UserLoginRequestDTO;

public interface AuthenticationService {
  public String authenticate(UserLoginRequestDTO dto);
}
