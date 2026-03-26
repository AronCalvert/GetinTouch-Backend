package com.cs4135.Backend.repository;

import org.springframework.data.repository.CrudRepository;
import com.cs4135.Backend.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {
}
