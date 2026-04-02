package com.cs4135.Backend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.AvailabilityResponseDTO;
import com.cs4135.Backend.dto.response.TimeSlotResponseDTO;
import com.cs4135.Backend.entity.Availability;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.entity.TimeSlot;

@Component
public class AvailabilityMapper {
  public Availability toAvailabilityEntity(AvailabilityCreationRequestDTO dto, Staff staff) {
    Availability availability = new Availability();

    availability.setStaff(staff);
    availability.setDay(dto.getDay());
    availability.setStartTime(dto.getStartTime());
    availability.setEndTime(dto.getEndTime());
    availability.setEndDate(dto.getEndDate());
    return availability;
  }

  public AvailabilityResponseDTO toAvailabilityDTO(Availability entity, long staffId) {


    return new AvailabilityResponseDTO(
        staffId,
        entity.getDay(),
        entity.getStartTime(),
        entity.getEndTime(),
        entity.getEndDate());
  }


}
