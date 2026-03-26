package com.cs4135.Backend.repository;

import org.springframework.data.repository.CrudRepository;
import com.cs4135.Backend.entity.Availability;

public interface AvailabilityRepository extends CrudRepository<Availability, Long> {
}
