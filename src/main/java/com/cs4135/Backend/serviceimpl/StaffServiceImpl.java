package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.repository.StaffRepository;
import com.cs4135.Backend.repository.StudentRepository;
import com.cs4135.Backend.service.StaffService;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import com.cs4135.Backend.mapper.StaffMapper;

import org.springframework.stereotype.Service;

import com.cs4135.Backend.dto.request.CreateStaffRequestDTO;
import com.cs4135.Backend.dto.response.StaffResponseDTO;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.security.AppPasswordEncoder;
import com.cs4135.Backend.entity.Student;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
  private final StaffRepository staffRepository;
  private final StaffMapper staffMapper;
  private final AppPasswordEncoder encoder;
  private final StudentRepository studentRepository;

  public StaffResponseDTO createStaff(CreateStaffRequestDTO dto) {
    String hashedPassword = encoder.encode(dto.getPassword());
    Staff staffMember = staffMapper.toStaffEntity(dto, hashedPassword);
    Staff savedStaff = staffRepository.save(staffMember);
    return staffMapper.toStaffDTO(savedStaff);
  }

  public List<StaffResponseDTO> getAllStaff() {
    return staffRepository.findAll()
        .stream()
        .map(staffMapper::toStaffDTO)
        .collect(Collectors.toList());

  }

  public StaffResponseDTO getStaffById(long id) {
    Staff staff = staffRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    return staffMapper.toStaffDTO(staff);
  }

  public List<StaffResponseDTO> getStaffForStudentDepartment(String email) {

    Student student = studentRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Student not found"));

    return staffRepository.findByDepartment(student.getDepartment())
        .stream()
        .map(staffMapper::toStaffDTO)
        .collect(Collectors.toList());
  }

  public List<StaffResponseDTO> getStaffForDepartment(String department) {
    return staffRepository.findByDepartment(department)
        .stream()
        .map(staffMapper::toStaffDTO)
        .collect(Collectors.toList());
  }

  public StaffResponseDTO getStaffByEmail(long id) {
    Staff staff = staffRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    return staffMapper.toStaffDTO(staff);
  }
}
