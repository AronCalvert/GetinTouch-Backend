package com.cs4135.Backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cs4135.Backend.entity.Availability;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {
}
