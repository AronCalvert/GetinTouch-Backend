package com.cs4135.Backend.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TimeSlot {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "availability_id", nullable = false)
  private Availability availability;

  private LocalDateTime startTime;
  private LocalDateTime endTime;

  private boolean isBooked;

  @OneToOne(mappedBy = "timeslot")
  private Meeting meeting;
}
