package com.cs4135.Backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs4135.Backend.dto.response.TimeSlotResponseDTO;
import com.cs4135.Backend.service.TimeSlotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/timeslots")
@RequiredArgsConstructor

public class TimeSlotController {
    private final TimeSlotService timeSlotService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<TimeSlotResponseDTO>> getStaffSlots(@PathVariable long staffId) {
        return ResponseEntity.ok(timeSlotService.getAvailableTimeslots(staffId));
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/book/{slotId}")
    public ResponseEntity<TimeSlotResponseDTO> markSlotBooked(@PathVariable long slotId) {
        return ResponseEntity.ok(timeSlotService.markSlotBooked(slotId));
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/free/{slotId}")
    public ResponseEntity<TimeSlotResponseDTO> markSlotFree(@PathVariable long slotId) {
        return ResponseEntity.ok(timeSlotService.markSlotFree(slotId));
    }
}
