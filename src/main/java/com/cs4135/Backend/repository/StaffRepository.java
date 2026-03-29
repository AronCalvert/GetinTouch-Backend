package com.cs4135.Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs4135.Backend.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
  List<Staff> findByDepartment(String department);
}
