package com.cs4135.Backend.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cs4135.Backend.mapper.AvailabilityMapper;
import com.cs4135.Backend.repository.AvailabilityRepository;
import com.cs4135.Backend.repository.StaffRepository;
import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.AvailabilityResponseDTO;
import com.cs4135.Backend.entity.Availability;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.service.AvailabilityService;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

  private final AvailabilityRepository availabilityRepository;
  private final AvailabilityMapper availabilityMapper;
  private final StaffRepository staffRepository;

  public List<AvailabilityResponseDTO> generateAvailability(List<AvailabilityCreationRequestDTO> dto) {
    return dto.stream()
        .map(this::generateSingleAvailability)
        .collect(Collectors.toList());
  }

  public AvailabilityResponseDTO generateSingleAvailability(AvailabilityCreationRequestDTO dto) {
    Staff staff = staffRepository.findById(dto.getStaffId())
        .orElseThrow(() -> new RuntimeException("Staff not found"));
    Availability entity = availabilityMapper.toAvailabilityEntity(dto, staff);
    Availability saved = availabilityRepository.save(entity);

    return availabilityMapper.toAvailabilityDTO(saved, staff.getId());
  }
}
