package com.cs4135.Backend.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student extends User {

  private long studentId;
  private String course;
  private int year;
  private String department;
}
