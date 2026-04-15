package com.cs4135.Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs4135.Backend.dto.response.StudentResponseDTO;
import com.cs4135.Backend.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping("dashboard")
  @PreAuthorize("hasRole('STUDENT')")
  public String dashboard() {
    return "welcome!";
  }

  @GetMapping("/me/{id}")
  @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
  public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable long id) {
    return ResponseEntity.ok(studentService.getStudentById(id));
  }
}
