package com.cs4135.Backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs4135.Backend.dto.request.CreateStaffRequestDTO;
import com.cs4135.Backend.dto.request.CreateStudentRequestDTO;
import com.cs4135.Backend.service.StaffService;
import com.cs4135.Backend.service.StudentService;
import com.cs4135.Backend.dto.response.StaffResponseDTO;
import com.cs4135.Backend.dto.response.StudentResponseDTO;

@RestController
@RequestMapping("/register")
public class RegistrationController {

  StudentService studentService;
  StaffService staffService;

  public RegistrationController(StudentService studentService, StaffService staffService) {
    this.studentService = studentService;
    this.staffService = staffService;
  }

  @PostMapping("/students")
  public StudentResponseDTO registerStudent(@RequestBody CreateStudentRequestDTO dto) {
    return studentService.createStudent(dto);
  }

  @PostMapping("/staff")
  public StaffResponseDTO registerStaff(@RequestBody CreateStaffRequestDTO dto) {
    return staffService.createStaff(dto);
  }
}
