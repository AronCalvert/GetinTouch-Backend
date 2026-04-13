package com.cs4135.Backend.controller;

import com.cs4135.Backend.dto.request.CreateConversationRequestDTO;
import com.cs4135.Backend.dto.request.SendMessageRequestDTO;
import com.cs4135.Backend.dto.response.ConversationResponseDTO;
import com.cs4135.Backend.dto.response.MessageResponseDTO;
import com.cs4135.Backend.service.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PreAuthorize("hasRole('STAFF')")
    @PostMapping
    public ResponseEntity<ConversationResponseDTO> createConversation(
            @Valid @RequestBody CreateConversationRequestDTO request,
            Authentication authentication
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conversationService.createOrGetConversation(request.getStudentId(), authentication.getName()));
    }

    @PreAuthorize("hasRole('STAFF') or hasRole('STUDENT')")
    @GetMapping
    public ResponseEntity<List<ConversationResponseDTO>> getConversations(Authentication authentication) {
        return ResponseEntity.ok(conversationService.getConversationsForUser(authentication.getName()));
    }

    @PreAuthorize("hasRole('STAFF') or hasRole('STUDENT')")
    @GetMapping("/{id}/messages")
    public ResponseEntity<List<MessageResponseDTO>> getMessages(@PathVariable Long id) {
        return ResponseEntity.ok(conversationService.getMessages(id));
    }

    @PreAuthorize("hasRole('STAFF') or hasRole('STUDENT')")
    @PostMapping("/{id}/messages")
    public ResponseEntity<MessageResponseDTO> sendMessage(
            @PathVariable Long id,
            @Valid @RequestBody SendMessageRequestDTO request,
            Authentication authentication
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conversationService.sendMessage(id, request.getContent(), authentication.getName()));
    }
}
