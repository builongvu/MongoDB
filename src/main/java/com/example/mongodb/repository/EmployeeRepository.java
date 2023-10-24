package com.example.mongodb.repository;

import com.example.mongodb.document.Author;
import com.example.mongodb.document.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
