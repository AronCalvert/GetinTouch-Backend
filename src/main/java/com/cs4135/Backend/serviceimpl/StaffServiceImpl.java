package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.repository.StaffRepository;
import com.cs4135.Backend.service.StaffService;

import lombok.RequiredArgsConstructor;

import com.cs4135.Backend.mapper.StaffMapper;
import com.cs4135.Backend.dto.request.CreateStaffRequestDTO;
import com.cs4135.Backend.dto.response.StaffResponseDTO;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.security.AppPasswordEncoder;

@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
  private StaffRepository staffRepository;
  private StaffMapper staffMapper;
  private AppPasswordEncoder encoder;

  public StaffResponseDTO createStaff(CreateStaffRequestDTO dto) {
    String hashedPassword = encoder.encode(dto.getPassword());
    Staff staffMember = staffMapper.toStaffEntity(dto, hashedPassword);
    Staff savedStaff = staffRepository.save(staffMember);
    return staffMapper.toStaffDTO(savedStaff);
  }
}
