package com.cs4135.Backend.mapper;

import com.cs4135.Backend.dto.request.CreateStaffRequestDTO;
import com.cs4135.Backend.dto.response.StaffResponseDTO;
import com.cs4135.Backend.entity.Staff;

public class StaffMapper {

  public Staff toStaffEntity(CreateStaffRequestDTO dto, String hashedPassword) {
    Staff staff = new Staff();

    staff.setName(dto.getName());
    staff.setEmail(dto.getEmail());
    staff.setHashedPassword(hashedPassword);
    staff.setDepartment(dto.getDepartment());
    staff.setTitle(dto.getTitle());
    staff.setOfficeLocation(dto.getOfficeLocation());

    return staff;
  }

  public StaffResponseDTO toStaffDTO(Staff staff) {
    return new StaffResponseDTO(
        staff.getId(),
        staff.getName(),
        staff.getEmail(),
        staff.getOfficeLocation(),
        staff.getTitle(),
        staff.getDepartment());
  }
}
