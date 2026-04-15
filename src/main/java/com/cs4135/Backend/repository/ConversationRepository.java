package com.cs4135.Backend.repository;

import com.cs4135.Backend.entity.Conversation;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByStaffAndStudent(Staff staff, Student student);
    List<Conversation> findByStaff(Staff staff);
    List<Conversation> findByStudent(Student student);
}
