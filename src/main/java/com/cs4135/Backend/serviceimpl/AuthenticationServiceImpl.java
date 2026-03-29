package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.dto.request.UserLoginRequestDTO;
import com.cs4135.Backend.service.AuthenticationService;
import com.cs4135.Backend.security.JwtService;
import com.cs4135.Backend.entity.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public String authenticate(UserLoginRequestDTO dto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

    User user = (User) authentication.getPrincipal();
    return jwtService.generateToken(user, user.getRole());
  }
}
