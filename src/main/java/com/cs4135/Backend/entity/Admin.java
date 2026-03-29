package com.cs4135.Backend.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends User {

  @Override
  public String getRole() {
    return "ROLE_ADMIN";
  }
}
