package com.example.new_starter.repo;

import com.example.new_starter.model.entity.EmployeePackageAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeePackageAssignmentRepository extends JpaRepository<EmployeePackageAssignment, Long> {
    List<EmployeePackageAssignment> findByEmployeeId(Long employeeId);
}
