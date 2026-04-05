package com.cs4135.Backend.controller;

import com.cs4135.Backend.dto.request.BookMeetingRequestDTO;
import com.cs4135.Backend.dto.response.MeetingResponseDTO;
import com.cs4135.Backend.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/")
    public ResponseEntity<MeetingResponseDTO> bookMeeting(@Valid @RequestBody BookMeetingRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.bookMeeting(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MeetingResponseDTO> getMeetingById(@PathVariable long id) {
        return ResponseEntity.ok(meetingService.getMeetingById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<MeetingResponseDTO> updateMeetingStatus(
            @PathVariable long id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(meetingService.updateMeetingStatus(id, status));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MeetingResponseDTO>> getMeetingsForStudent(@PathVariable long studentId) {
        return ResponseEntity.ok(meetingService.getMeetingsForStudent(studentId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<MeetingResponseDTO>> getMeetingsForStaff(@PathVariable long staffId) {
        return ResponseEntity.ok(meetingService.getMeetingsForStaff(staffId));
    }
}
