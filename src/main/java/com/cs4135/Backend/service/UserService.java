package com.cs4135.Backend.service;

import com.cs4135.Backend.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
  public List<UserResponseDTO> getAllUsers();

  public UserResponseDTO getUserById(Long id);
}
