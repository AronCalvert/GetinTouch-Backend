package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.dto.request.UserLoginRequestDTO;
import com.cs4135.Backend.repository.UserRepository;
import com.cs4135.Backend.service.AuthenticationService;
import com.cs4135.Backend.security.JwtService;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.entity.Student;
import com.cs4135.Backend.entity.Admin;
import com.cs4135.Backend.entity.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserRepository userRepository;

  public String authenticate(UserLoginRequestDTO dto) {

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        dto.getEmail(),
        dto.getPassword());

    Authentication authentication = authenticationManager.authenticate(authToken);
    UserDetails user = (UserDetails) authentication.getPrincipal();
    return jwtService.generateToken(user, getRoleFromUser(dto));
  }

  private String getRoleFromUser(UserLoginRequestDTO dto) {
    User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    if (user instanceof Admin)
      return "ROLE_ADMIN";
    if (user instanceof Student)
      return "ROLE_STUDENT";
    if (user instanceof Staff)
      return "ROLE_STAFF";
    throw new IllegalArgumentException("Unknown user type");
  }
}
