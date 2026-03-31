package com.cs4135.Backend.controller;

import com.cs4135.Backend.dto.request.BookMeetingRequestDTO;
import com.cs4135.Backend.dto.response.MeetingResponseDTO;
import com.cs4135.Backend.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
