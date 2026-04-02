package com.cs4135.Backend.service;

import java.util.List;

import com.cs4135.Backend.dto.response.TimeSlotResponseDTO;

public interface TimeSlotService {
    public List<TimeSlotResponseDTO> getAvailableTimeslots(long staffId);
    public TimeSlotResponseDTO markSlotBooked(long slotId);
    public TimeSlotResponseDTO markSlotFree(long slotId);
}
