package com.cs4135.Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cs4135.Backend.entity.Availability;
import com.cs4135.Backend.entity.TimeSlot;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
    @Query("Select t from TimeSlot t where t.availability.staff.id = :staffId AND t.isBooked = false")
    List<TimeSlot> findByAvailabilityByStaffId(@Param("staffId")long staffId);
}
