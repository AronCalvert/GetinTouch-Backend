package com.cs4135.Backend.mapper;

import org.springframework.stereotype.Component;

import com.cs4135.Backend.dto.request.CreateAdminRequestDTO;
import com.cs4135.Backend.dto.response.AdminResponseDTO;
import com.cs4135.Backend.entity.Admin;

@Component
public class AdminMapper {
  public Admin AdmintoUserEntity(CreateAdminRequestDTO dto, String hashedPassword) {
    Admin admin = new Admin();

    admin.setName(dto.getName());
    admin.setEmail(dto.getEmail());
    admin.setHashedPassword(hashedPassword);
    return admin;
  }

  public AdminResponseDTO toAdminResponseDTO(Admin admin) {
    return new AdminResponseDTO(
        admin.getId(),
        admin.getEmail(),
        admin.getName(),
        admin.getCreatedAt());
  }
}
