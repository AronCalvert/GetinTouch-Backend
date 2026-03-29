package com.cs4135.Backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cs4135.Backend.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
  Optional<Student> findByEmail(String email);
}
