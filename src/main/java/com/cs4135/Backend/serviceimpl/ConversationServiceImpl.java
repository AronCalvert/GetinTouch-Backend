package com.cs4135.Backend.serviceimpl;

import com.cs4135.Backend.dto.response.ConversationResponseDTO;
import com.cs4135.Backend.dto.response.MessageResponseDTO;
import com.cs4135.Backend.entity.*;
import com.cs4135.Backend.mapper.ConversationMapper;
import com.cs4135.Backend.mapper.MessageMapper;
import com.cs4135.Backend.repository.*;
import com.cs4135.Backend.service.ConversationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final ConversationMapper conversationMapper;
    private final MessageMapper messageMapper;

    @Override
    @Transactional
    public ConversationResponseDTO createOrGetConversation(Long studentId, String staffEmail) {
        Staff staff = staffRepository.findByEmail(staffEmail)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Conversation conversation = conversationRepository.findByStaffAndStudent(staff, student)
                .orElseGet(() -> {
                    Conversation newConversation = new Conversation();
                    newConversation.setStaff(staff);
                    newConversation.setStudent(student);
                    return conversationRepository.save(newConversation);
                });

        return conversationMapper.toResponseDTO(conversation);
    }

    @Override
    @Transactional
    public List<ConversationResponseDTO> getConversationsForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<Conversation> conversations;

        if (user instanceof Staff staff) {
            conversations = conversationRepository.findByStaff(staff);
        } else if (user instanceof Student student) {
            conversations = conversationRepository.findByStudent(student);
        } else {
            throw new IllegalStateException("Conversations are only available to staff and students");
        }

        return conversations.stream()
                .map(conversationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MessageResponseDTO> getMessages(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new EntityNotFoundException("Conversation not found"));

        return messageRepository.findByConversationOrderBySentAtAsc(conversation).stream()
                .map(messageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MessageResponseDTO sendNotification(Long staffId, String studentEmail, String content) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        Student student = studentRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Conversation conversation = conversationRepository.findByStaffAndStudent(staff, student)
                .orElseGet(() -> {
                    Conversation newConversation = new Conversation();
                    newConversation.setStaff(staff);
                    newConversation.setStudent(student);
                    return conversationRepository.save(newConversation);
                });

        Message message = new Message();
        message.setConversation(conversation);
        message.setSender(student);
        message.setContent(content);

        return messageMapper.toResponseDTO(messageRepository.save(message));
    }

    @Override
    @Transactional
    public MessageResponseDTO sendMessage(Long conversationId, String content, String senderEmail) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new EntityNotFoundException("Conversation not found"));

        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Message message = new Message();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setContent(content);

        return messageMapper.toResponseDTO(messageRepository.save(message));
    }
}
