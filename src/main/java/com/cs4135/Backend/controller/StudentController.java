package com.cs4135.Backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

  @GetMapping("dashboard")
  @PreAuthorize("hasRole('student')")
  public String dashboard() {
    return "welcome!";
  }
}
