package com.cs4135.Backend.security;

import org.springframework.stereotype.Component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class AppPasswordEncoder implements PasswordEncoder {
  private final PasswordEncoder encoder = new BCryptPasswordEncoder(16);

  @Override
  public String encode(CharSequence rawPassword) {
    return encoder.encode(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }
}
