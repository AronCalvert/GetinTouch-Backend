package com.cs4135.Backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
public class Availability {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "staff_id", nullable = false)
  private Staff staff;

  private LocalDate date;

  private LocalTime startTime;
  private LocalTime endTime;

  @OneToMany(mappedBy = "availability", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TimeSlot> timeslots = new ArrayList<>();
}
