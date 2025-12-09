package com.example.new_starter.model.dto;

import com.example.new_starter.model.entity.EmploymentPackage;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePackageAssignmentDTO {
    private Long id;
    private Long employeeId;
    private EmploymentPackageDTO employmentPackage;
}
