package com.cs4135.Backend.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.AvailabilityResponseDTO;
import com.cs4135.Backend.service.AvailabilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Availability")
@RequiredArgsConstructor
public class AvailabilityController {

  private final AvailabilityService availabilityService;

  @PreAuthorize("hasRole('STAFF')")
  @PostMapping("/")
  public ResponseEntity<List<AvailabilityResponseDTO>> createAvailability(List<AvailabilityCreationRequestDTO> dto) {
    return ResponseEntity.ok(availabilityService.generateAvailability(dto));

  }
}
