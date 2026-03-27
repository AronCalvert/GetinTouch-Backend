package com.cs4135.Backend.mapper;

import org.springframework.stereotype.Component;

import com.cs4135.Backend.dto.request.CreateStudentRequestDTO;
import com.cs4135.Backend.dto.response.StudentResponseDTO;
import com.cs4135.Backend.entity.Student;

@Component
public class StudentMapper {
  public Student toStudentEntity(CreateStudentRequestDTO dto, String hashedPassword) {
    Student student = new Student();
    student.setName(dto.getName());
    student.setEmail(dto.getEmail());
    student.setHashedPassword(hashedPassword);
    student.setStudentId(dto.getStudentId());
    student.setCourse(dto.getCourse());
    student.setYear(dto.getYear());
    student.setDepartment(dto.getDepartment());
    return student;
  }

  public StudentResponseDTO toStudentDTO(Student student) {
    return new StudentResponseDTO(
        student.getId(),
        student.getName(),
        student.getEmail(),
        student.getStudentId(),
        student.getCourse(),
        student.getYear(),
        student.getDepartment());
  }
}
