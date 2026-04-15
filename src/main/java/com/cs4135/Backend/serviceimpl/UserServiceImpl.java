package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.entity.Student;
import com.cs4135.Backend.repository.MeetingRepository;
import com.cs4135.Backend.repository.UserRepository;
import com.cs4135.Backend.service.UserService;
import com.cs4135.Backend.dto.response.UserResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
  private final MeetingRepository meetingRepository;
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

  public UserResponseDTO getUserByEmail(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    return userMapper.toUserResponseDTO(user);
  }

  @Transactional
  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

    if (user instanceof Staff staff && !meetingRepository.findByStaffId(staff.getId()).isEmpty()) {
      throw new IllegalStateException("Cannot delete staff with existing meetings");
    }
    if (user instanceof Student student && !meetingRepository.findByStudentId(student.getId()).isEmpty()) {
      throw new IllegalStateException("Cannot delete student with existing meetings");
    }

    userRepository.delete(user);
  }
}
