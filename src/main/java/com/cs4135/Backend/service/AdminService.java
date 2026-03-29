package com.cs4135.Backend.service;

import com.cs4135.Backend.dto.request.CreateAdminRequestDTO;
import com.cs4135.Backend.dto.response.AdminResponseDTO;

public interface AdminService {
  public AdminResponseDTO createAdmin(CreateAdminRequestDTO dto);
}
