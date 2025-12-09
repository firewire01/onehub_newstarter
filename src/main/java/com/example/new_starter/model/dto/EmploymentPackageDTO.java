package com.example.new_starter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentPackageDTO {

    private Long id;

    private String name; // e.g., "Health Insurance", "Stock Options", etc.

    private String description;
}
