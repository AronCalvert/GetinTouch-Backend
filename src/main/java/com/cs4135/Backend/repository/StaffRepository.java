package com.cs4135.Backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cs4135.Backend.entity.Staff;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {

}
