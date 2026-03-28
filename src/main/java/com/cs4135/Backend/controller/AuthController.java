package com.cs4135.Backend.controller;

import com.cs4135.Backend.dto.response.LoginResponseDTO;
import com.cs4135.Backend.dto.request.UserLoginRequestDTO;
import com.cs4135.Backend.dto.request.CreateStudentRequestDTO;
import com.cs4135.Backend.dto.request.CreateStaffRequestDTO;
import com.cs4135.Backend.dto.response.StudentResponseDTO;
import com.cs4135.Backend.dto.response.StaffResponseDTO;
import com.cs4135.Backend.service.AuthenticationService;
import com.cs4135.Backend.service.StaffService;
import com.cs4135.Backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;
  private final StaffService staffService;
  private final StudentService studentService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginRequestDTO dto) {
    String token = authenticationService.authenticate(dto);
    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  @PostMapping("/register/student")
  public ResponseEntity<String> registerStudent(@RequestBody CreateStudentRequestDTO dto) {
    StudentResponseDTO studentResponseDTO = studentService.createStudent(dto);
    return ResponseEntity.ok("Student registered with email: " + studentResponseDTO);
  }

  @PostMapping("/register/staff")
  public ResponseEntity<String> registerStaff(@RequestBody CreateStaffRequestDTO dto) {
    StaffResponseDTO staffResponseDTO = staffService.createStaff(dto);
    return ResponseEntity.ok("Staff registered with email: " + staffResponseDTO);
  }
}
