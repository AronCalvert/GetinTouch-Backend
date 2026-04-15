package com.cs4135.Backend.service;

import java.util.List;

import com.cs4135.Backend.dto.response.TimeSlotResponseDTO;

public interface TimeSlotService {
    public List<TimeSlotResponseDTO> getAvailableTimeslots(long staffId);
    public List<TimeSlotResponseDTO> getAllTimeslots(long staffId);
    public TimeSlotResponseDTO markSlotBooked(long slotId, String note);
    public TimeSlotResponseDTO markSlotFree(long slotId);
}
