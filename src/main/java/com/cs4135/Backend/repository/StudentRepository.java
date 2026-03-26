package com.cs4135.Backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.cs4135.Backend.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
