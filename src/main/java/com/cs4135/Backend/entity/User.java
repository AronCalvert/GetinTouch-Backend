package com.cs4135.Backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @Column(unique = true)
  private String email;

  private String hashedPassword;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
