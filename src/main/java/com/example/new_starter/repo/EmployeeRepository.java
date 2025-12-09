package com.example.new_starter.repo;


import com.example.new_starter.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
Optional<Employee> findByEmail(String email);
}