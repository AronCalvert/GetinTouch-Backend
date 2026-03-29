package com.cs4135.Backend.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cs4135.Backend.mapper.AvailabilityMapper;
import com.cs4135.Backend.repository.AvailabilityRepository;
import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.AvailabilityResponseDTO;
import com.cs4135.Backend.service.AvailabilityService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

  private final AvailabilityRepository availabilityRepository;
  private final AvailabilityMapper availabilityMapper;

  public List<AvailabilityResponseDTO> generateAvailability(List<AvailabilityCreationRequestDTO> dto) {
    dto.stream()
        .map(availabilityMapper::toAvailabilityEntity)
        .collect(Collectors.toList());

  }
}
