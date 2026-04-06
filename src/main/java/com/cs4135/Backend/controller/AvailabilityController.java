package com.cs4135.Backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.AvailabilityResponseDTO;
import com.cs4135.Backend.service.AvailabilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Availability")
@RequiredArgsConstructor
public class AvailabilityController {

  private final AvailabilityService availabilityService;

  @PreAuthorize("hasRole('STAFF')")
  @PostMapping("/")
  public ResponseEntity<List<AvailabilityResponseDTO>> createAvailability(@RequestBody List<AvailabilityCreationRequestDTO> dto,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return ResponseEntity.ok(availabilityService.generateAvailability(dto,date));
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/staff/{staffId}")
  public ResponseEntity<List<AvailabilityResponseDTO>> getAvailabilityByStaff(@PathVariable long staffId) {
    return ResponseEntity.ok(availabilityService.getAvailabilityByStaff(staffId));
  }

  @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAvailability(@PathVariable long id) {
    availabilityService.deleteAvailability(id);
    return ResponseEntity.noContent().build();
  }

  @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<AvailabilityResponseDTO> updateAvailability(@PathVariable long id, @RequestBody AvailabilityCreationRequestDTO dto) {
    AvailabilityResponseDTO updated = availabilityService.updateAvailability(id, dto);
    return ResponseEntity.ok(updated);
  }

  
}
