package com.cs4135.Backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.cs4135.Backend.entity.Meeting;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.entity.enums.Status;

import java.util.List;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
    List<Meeting> findByStaffAndStatus(Staff staff, Status status);
}
