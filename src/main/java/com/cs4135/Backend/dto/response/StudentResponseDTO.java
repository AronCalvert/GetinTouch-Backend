package com.cs4135.Backend.dto.response;

import lombok.Getter;

@Getter
public class StudentResponseDTO {

  private Long id;
  private String name;
  private String email;
  private long studentId;
  private String course;
  private int year;
  private String department;

  public StudentResponseDTO(
      Long id,
      String name,
      String email,
      long studentId,
      String course,
      int year,
      String department) {
    this.studentId = studentId;
    this.course = course;
    this.year = year;
    this.department = department;
    this.id = id;
    this.name = name;
    this.email = email;
  }
}
