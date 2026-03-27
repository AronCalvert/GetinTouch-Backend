package com.cs4135.Backend.entity;

import com.cs4135.Backend.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Meeting {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "staff_id", nullable = false)
  private Staff staff;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @OneToOne
  @JoinColumn(name = "timeslot_id", nullable = false, unique = true)
  private TimeSlot timeslot;

  @Enumerated(EnumType.STRING)
  private Status status;

  private String notes;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
