package com.cs4135.Backend.serviceimpl;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cs4135.Backend.mapper.AvailabilityMapper;
import com.cs4135.Backend.mapper.TimeSlotMapper;
import com.cs4135.Backend.repository.AvailabilityRepository;
import com.cs4135.Backend.repository.StaffRepository;
import com.cs4135.Backend.dto.request.AvailabilityCreationRequestDTO;
import com.cs4135.Backend.dto.response.AvailabilityResponseDTO;
import com.cs4135.Backend.entity.Availability;
import com.cs4135.Backend.entity.Staff;
import com.cs4135.Backend.entity.TimeSlot;
import com.cs4135.Backend.service.AvailabilityService;

import jakarta.transaction.Transactional;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

  private final AvailabilityRepository availabilityRepository;
  private final AvailabilityMapper availabilityMapper;
  private final StaffRepository staffRepository;
  private final TimeSlotServiceImpl timeSlotServiceImpl;
  private final TimeSlotMapper timeSlotMapper;
  @Transactional
  public List<AvailabilityResponseDTO> generateAvailability(List<AvailabilityCreationRequestDTO> dtoList,LocalDate date) {
    return generateSingleAvailability(dtoList, date);
  }
        

  public List<AvailabilityResponseDTO> generateSingleAvailability(List<AvailabilityCreationRequestDTO> dtos, LocalDate date) {
    List<AvailabilityResponseDTO> finalRes = new ArrayList<>();
    for(AvailabilityCreationRequestDTO dto: dtos) {

      Staff staff = staffRepository.findById(dto.getStaffId())//Creates a new staff using id in dto
        .orElseThrow(() -> new RuntimeException("Staff not found"));//none found throw error
      Availability saved = null;
      ArrayList<TimeSlot> allGenSlots = new ArrayList<>();  
      for(LocalDate curDate = date;!curDate.isAfter(dto.getEndDate());curDate = curDate.plusDays(7)){
        Availability entity = availabilityMapper.toAvailabilityEntity(dto, staff);//Uses mapper to construct Availability out of the DTO and staff entity
        saved = availabilityRepository.save(entity);//saves it to the database 
        ArrayList<TimeSlot> slots = timeSlotServiceImpl.generateTimeSlotsForWindow(dto,entity,curDate);
        finalRes.add(availabilityMapper.toAvailabilityDTO(saved,staff.getId()));
    }
  }


    return finalRes;
  }

  public List<AvailabilityResponseDTO> getAvailabilityByStaff(long staffId) {
    List<Availability> availabilities = availabilityRepository.findByStaffId(staffId);
    return availabilities.stream()
    .map(availability -> availabilityMapper.toAvailabilityDTO(
      availability,
      staffId)
    )
      .collect(Collectors.toList());
  }

  public void deleteAvailability(long id) {
    if(!availabilityRepository.existsById(id)) {
      throw new RuntimeException("Availability not found with this id "+ id);
    }
    availabilityRepository.deleteById(id);
  }

  @Transactional
  public AvailabilityResponseDTO updateAvailability(long id, AvailabilityCreationRequestDTO dto) {
    Availability availability = availabilityRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Availability not Found"));

    availability.setDay(dto.getDay());
    availability.setStartTime(dto.getStartTime());
    availability.setEndTime(dto.getEndTime());
    availability.setEndDate(dto.getEndDate());

    availability.getTimeslots().clear();

    timeSlotServiceImpl.generateTimeSlotsForWindow(dto, availability, LocalDate.now());
    
    Availability saved = availabilityRepository.save(availability);

    return availabilityMapper.toAvailabilityDTO(saved, availability.getStaff().getId());
  }

}
