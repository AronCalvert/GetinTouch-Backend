package com.cs4135.Backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cs4135.Backend.dto.response.TimeSlotResponseDTO;
import com.cs4135.Backend.entity.TimeSlot;
@Component
public class TimeSlotMapper {
    public TimeSlotResponseDTO toDto(TimeSlot entity) {
        return new TimeSlotResponseDTO(
            entity.getId(),
            entity.getAvailability().getId(),
            entity.getStartTime(),
            entity.getEndTime(),
            entity.isBooked()
        );
    }

    public List<TimeSlotResponseDTO> toDtoList(List<TimeSlot> entities) {
        if (entities.isEmpty()) return new ArrayList<>();
        return entities.stream().map(this::toDto).toList();
    }
}
