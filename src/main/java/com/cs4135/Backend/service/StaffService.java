package com.cs4135.Backend.service;

import com.cs4135.Backend.dto.request.CreateStaffRequestDTO;
import com.cs4135.Backend.dto.response.StaffResponseDTO;

public interface StaffService {
  public StaffResponseDTO createStaff(CreateStaffRequestDTO dto);
}
