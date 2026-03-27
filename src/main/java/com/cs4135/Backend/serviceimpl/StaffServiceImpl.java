package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.repository.StaffRepository;
import com.cs4135.Backend.service.StaffService;

import lombok.RequiredArgsConstructor;

import com.cs4135.Backend.mapper.StaffMapper;

import org.springframework.stereotype.Service;

import com.cs4135.Backend.dto.request.CreateStaffRequestDTO;
import com.cs4135.Backend.dto.response.StaffResponseDTO;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.security.AppPasswordEncoder;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
  private final StaffRepository staffRepository;
  private final StaffMapper staffMapper;
  private final AppPasswordEncoder encoder;

  public StaffResponseDTO createStaff(CreateStaffRequestDTO dto) {
    String hashedPassword = encoder.encode(dto.getPassword());
    Staff staffMember = staffMapper.toStaffEntity(dto, hashedPassword);
    Staff savedStaff = staffRepository.save(staffMember);
    return staffMapper.toStaffDTO(savedStaff);
  }
}
