package com.cs4135.Backend.service;

import com.cs4135.Backend.dto.request.CreateStaffRequestDTO;
import com.cs4135.Backend.dto.response.StaffResponseDTO;
import java.util.List;

public interface StaffService {
  public StaffResponseDTO createStaff(CreateStaffRequestDTO dto);

  public List<StaffResponseDTO> getAllStaff();

  public StaffResponseDTO getStaffById(long id);

  public List<StaffResponseDTO> getStaffForStudentDepartment(String email);

  public List<StaffResponseDTO> getStaffForDepartment(String department);

  public StaffResponseDTO getStaffByEmail(long id);
}
