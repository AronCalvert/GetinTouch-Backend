package com.cs4135.Backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs4135.Backend.service.StaffService;
import com.cs4135.Backend.dto.response.StaffResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

  public final StaffService staffService;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/")
  public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
    List<StaffResponseDTO> staff = staffService.getAllStaff();
    return ResponseEntity.ok(staff);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/id/{id}")
  public ResponseEntity<StaffResponseDTO> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(staffService.getStaffById(id));
  }

  @GetMapping("/my-department")
  @PreAuthorize("hasRole('STUDENT')")
  public ResponseEntity<List<StaffResponseDTO>> getMyDepartmentStaff(Authentication authentication) {
    return ResponseEntity.ok(staffService.getStaffForStudentDepartment(authentication.getName()));
  }

  @GetMapping("/department/{department}")
  @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
  public ResponseEntity<List<StaffResponseDTO>> getDepartmentStaff(@PathVariable String department) {
    return ResponseEntity.ok(staffService.getStaffForDepartment(department));
  }
}
