package com.example.new_starter.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_package_assignment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePackageAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private EmploymentPackage employmentPackage;
}
