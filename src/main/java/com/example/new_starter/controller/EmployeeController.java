package com.example.new_starter.controller;

import com.example.new_starter.model.dto.EmployeeDTO;
import com.example.new_starter.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Validated
@RequiredArgsConstructor   // Lombok generates constructor for final fields
@Tag(name = "Employee Management", description = "CRUD APIs for Employee resources")
public class EmployeeController {

    private final EmployeeService service;

    @Operation(summary = "Create a new employee")
    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO dto) {
        EmployeeDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(
            @Parameter(description = "Employee ID") @PathVariable Long id) {

        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @Operation(summary = "Update an employee")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @Parameter(description = "Employee ID") @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody EmployeeDTO employeeDTO) {

        return ResponseEntity.ok(service.updateEmployee(id, employeeDTO));
    }

    @Operation(summary = "Delete an employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @Parameter(description = "Employee ID") @PathVariable Long id) {

        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
