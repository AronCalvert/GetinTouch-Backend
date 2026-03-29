package com.cs4135.Backend.mapper;

import org.springframework.stereotype.Component;

import com.cs4135.Backend.dto.response.UserResponseDTO;
import com.cs4135.Backend.entity.User;

@Component
public class UserMapper {
  public UserResponseDTO toUserResponseDTO(User user) {
    return new UserResponseDTO(
        user.getId(),
        user.getEmail(),
        user.getName(),
        user.getCreatedAt());
  }
}
