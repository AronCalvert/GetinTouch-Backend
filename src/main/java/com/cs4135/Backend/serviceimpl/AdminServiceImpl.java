package com.cs4135.Backend.serviceimpl;

import org.springframework.stereotype.Service;

import com.cs4135.Backend.dto.request.CreateAdminRequestDTO;
import com.cs4135.Backend.dto.response.AdminResponseDTO;
import com.cs4135.Backend.entity.Admin;
import com.cs4135.Backend.mapper.AdminMapper;
import com.cs4135.Backend.repository.AdminRepository;
import com.cs4135.Backend.security.AppPasswordEncoder;
import com.cs4135.Backend.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final AdminRepository adminRepository;
  private final AdminMapper adminMapper;
  private final AppPasswordEncoder encoder;

  public AdminResponseDTO createAdmin(CreateAdminRequestDTO dto) {
    String hashedPassword = encoder.encode(dto.getPassword());
    Admin admin = adminMapper.AdmintoUserEntity(dto, hashedPassword);
    Admin savedAdmin = adminRepository.save(admin);
    return adminMapper.toAdminResponseDTO(savedAdmin);
  }
}
