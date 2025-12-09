package com.example.new_starter.service;

import com.example.new_starter.config.mapper.EmployeePackageAssignmentMapper;
import com.example.new_starter.model.dto.EmployeePackageAssignmentDTO;
import com.example.new_starter.model.dto.EmploymentPackageDTO;
import com.example.new_starter.model.entity.Employee;
import com.example.new_starter.model.entity.EmployeePackageAssignment;
import com.example.new_starter.model.entity.EmploymentPackage;
import com.example.new_starter.repo.EmployeePackageAssignmentRepository;
import com.example.new_starter.repo.EmploymentPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeePackageService {

    private final EmployeePackageAssignmentRepository assignmentRepository;
    private final EmploymentPackageRepository packageRepository;
    private final EmployeePackageAssignmentMapper mapper;

    // Assign packages to employee
    public List<EmployeePackageAssignmentDTO> assignPackages(Long employeeId, List<Long> packageIds) {
        List<EmploymentPackage> packages = packageRepository.findAllById(packageIds);

        List<EmployeePackageAssignment> assignments = packages.stream()
                .map(p -> EmployeePackageAssignment.builder()
                        .employeeId(employeeId)
                        .employmentPackage(p)
                        .build())
                .collect(Collectors.toList());

        assignmentRepository.saveAll(assignments);

        return assignments.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // Get all packages assigned to an employee
    public List<EmployeePackageAssignmentDTO> getPackages(Long employeeId) {
        return assignmentRepository.findByEmployeeId(employeeId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public EmploymentPackageDTO createPackage(EmploymentPackageDTO employmentPackageDTO) {

        if (packageRepository.existsByName(employmentPackageDTO.getName())) {
            throw new IllegalArgumentException("Package name already exists: " + employmentPackageDTO.getName());
        }

        employmentPackageDTO.setId(null); //clear for creation.

        EmploymentPackage aPackage = mapper.employeePackageToEntity(employmentPackageDTO);

        EmploymentPackage createdPackage = packageRepository.save(aPackage);

        return mapper.employeePackageToDto(createdPackage);

    }

    public EmploymentPackageDTO updatePackage(Long id , EmploymentPackageDTO employmentPackageDTO) {
        EmploymentPackage existing = packageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee package not found with id: " + id));

        // Ensure package name is unique (ignore current record)
        if (packageRepository.existsByNameAndIdNot(employmentPackageDTO.getName(), id)) {
            throw new RuntimeException("Another package already exists with name: " + employmentPackageDTO.getName());
        }

        mapper.updateEmployeePackage(employmentPackageDTO, existing);
        EmploymentPackage updated = packageRepository.save(existing);

        return mapper.employeePackageToDto(updated);

    }

    public void deletePackage(Long id) {
        // First, retrieve the EmploymentPackage
        EmploymentPackage existing = packageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee package not found with id: " + id));

        // Proceed with deletion
        packageRepository.delete(existing);
    }

    // Get all Employment Packages
    public List<EmploymentPackageDTO> getAllPackages() {
        List<EmploymentPackage> allPackages = packageRepository.findAll();
        return allPackages.stream()
                .map(mapper::employeePackageToDto)
                .collect(Collectors.toList());
    }

}
