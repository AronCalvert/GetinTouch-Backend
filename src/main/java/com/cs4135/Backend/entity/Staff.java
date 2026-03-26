package com.cs4135.Backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Entity
@Getter
@Setter
public class Staff extends User {

  private String department;
  private String title;
  private String officeLocation;

  @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
  private ArrayList<Availability> availabilities = new ArrayList<>();
}
