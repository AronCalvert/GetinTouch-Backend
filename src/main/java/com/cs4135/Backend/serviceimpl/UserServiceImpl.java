package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.repository.UserRepository;
import com.cs4135.Backend.service.UserService;
import com.cs4135.Backend.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import java.util.List;
import com.cs4135.Backend.entity.User;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cs4135.Backend.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<UserResponseDTO> getAllUsers() {
    return userRepository.findAll()
        .stream()
        .map(userMapper::toUserResponseDTO)
        .collect(Collectors.toList());
  }

  public UserResponseDTO getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    return userMapper.toUserResponseDTO(user);
  }
}
