package com.example.new_starter.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePackageAssignmentDTO {
    private Long employeeId;
    private Long packageId;
    private String packageName;
}
