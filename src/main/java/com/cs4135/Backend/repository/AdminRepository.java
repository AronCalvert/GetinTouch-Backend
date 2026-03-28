package com.cs4135.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs4135.Backend.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
