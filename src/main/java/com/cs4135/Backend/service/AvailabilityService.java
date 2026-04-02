package com.cs4135.Backend.service;

import java.time.LocalDate;
import java.util.List;

import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.AvailabilityResponseDTO;

public interface AvailabilityService {
  public List<AvailabilityResponseDTO> generateAvailability(List<AvailabilityCreationRequestDTO> dto,LocalDate date);
  public List<AvailabilityResponseDTO> getAvailabilityByStaff(long staffId);
  public void deleteAvailability(long id);
  public AvailabilityResponseDTO updateAvailability(long id, AvailabilityCreationRequestDTO dto);
}
