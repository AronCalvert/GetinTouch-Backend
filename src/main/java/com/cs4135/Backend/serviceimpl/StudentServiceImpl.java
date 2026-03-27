package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.service.StudentService;

import lombok.RequiredArgsConstructor;

import com.cs4135.Backend.dto.response.StudentResponseDTO;
import com.cs4135.Backend.entity.Student;
import com.cs4135.Backend.repository.StudentRepository;
import com.cs4135.Backend.dto.request.CreateStudentRequestDTO;
import com.cs4135.Backend.mapper.StudentMapper;
import com.cs4135.Backend.security.AppPasswordEncoder;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private StudentRepository studentRepository;
  private StudentMapper studentMapper;
  private AppPasswordEncoder encoder;

  public StudentResponseDTO createStudent(CreateStudentRequestDTO dto) {
    String hashedPassword = encoder.encode(dto.getPassword());
    Student student = studentMapper.toStudentEntity(dto, hashedPassword);
    Student saved = studentRepository.save(student);
    return studentMapper.toStudentDTO(saved);
  }
}
