package com.example.new_starter.controller;

import com.example.new_starter.model.dto.EmployeePackageAssignmentDTO;
import com.example.new_starter.model.dto.EmploymentPackageDTO;
import com.example.new_starter.service.EmployeePackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
@Tag(name = "Employee Packages", description = "Assign and retrieve employment packages for employees")
public class EmployeePackageController {

    private final EmployeePackageService service;

    @PostMapping("/{employeeId}")
    @Operation(summary = "Assign employment packages to an employee")
    public ResponseEntity<List<EmployeePackageAssignmentDTO>> assignPackages(
            @PathVariable Long employeeId,
            @RequestBody List<Long> packageIds) {
        return ResponseEntity.ok(service.assignPackages(employeeId, packageIds));
    }

    @PostMapping("/package")
    @Operation(summary = "Create employment package")
    public ResponseEntity<EmploymentPackageDTO> createPackage(
            @RequestBody EmploymentPackageDTO employmentPackageDTO) {
        return ResponseEntity.ok(service.createPackage(employmentPackageDTO));
    }

    // Get all Employment Packages
    @GetMapping
    @Operation(summary = "Get all employment packages")
    public ResponseEntity<List<EmploymentPackageDTO>> getAllPackages() {
        List<EmploymentPackageDTO> packages = service.getAllPackages();
        return ResponseEntity.ok(packages);
    }

    @PutMapping("/package/{id}")
    @Operation(summary = "Update employment package")
    public ResponseEntity<EmploymentPackageDTO> updatePackage(
            @PathVariable Long id, @RequestBody EmploymentPackageDTO employmentPackageDTO) {
        return ResponseEntity.ok(service.updatePackage(id,employmentPackageDTO));
    }

    @DeleteMapping("/package/{id}")
    @Operation(summary = "Delete an employment package")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        service.deletePackage(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{employeeId}")
    @Operation(summary = "Get all employment packages assigned to an employee")
    public ResponseEntity<List<EmployeePackageAssignmentDTO>> getPackages(@PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getPackages(employeeId));
    }
}
