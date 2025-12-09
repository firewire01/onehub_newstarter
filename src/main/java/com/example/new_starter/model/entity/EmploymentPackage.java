package com.example.new_starter.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employment_package")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // e.g., "Health Insurance", "Stock Options", etc.

    private String description;
}
