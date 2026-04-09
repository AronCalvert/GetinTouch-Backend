package com.cs4135.Backend.service;

import com.cs4135.Backend.dto.request.CreateStudentRequestDTO;
import com.cs4135.Backend.dto.response.StudentResponseDTO;

public interface StudentService {
  public StudentResponseDTO createStudent(CreateStudentRequestDTO dto);

  public StudentResponseDTO getStudentById(long id);
}
