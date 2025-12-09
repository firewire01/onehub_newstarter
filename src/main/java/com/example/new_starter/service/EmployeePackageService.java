package com.example.new_starter.service;

import com.example.new_starter.config.mapper.EmployeePackageAssignmentMapper;
import com.example.new_starter.model.dto.EmployeePackageAssignmentDTO;
import com.example.new_starter.model.dto.EmploymentPackageDTO;
import com.example.new_starter.model.entity.Employee;
import com.example.new_starter.model.entity.EmployeePackageAssignment;
import com.example.new_starter.model.entity.EmploymentPackage;
import com.example.new_starter.repo.EmployeePackageAssignmentRepository;
import com.example.new_starter.repo.EmployeeRepository;
import com.example.new_starter.repo.EmploymentPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeePackageService {

    private final EmployeePackageAssignmentRepository assignmentRepository;
    private final EmploymentPackageRepository packageRepository;
    private final EmployeePackageAssignmentMapper mapper;
    private final EmployeeRepository employeeRepository;

    // Assign packages to employee
    @Transactional
    public List<EmployeePackageAssignmentDTO> assignPackages(Long employeeId, List<Long> packageIds) {

        employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Employee does not exist. id: " + employeeId)
                );


        packageIds.forEach(pi ->
                packageRepository.findById(pi)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Package does not exist. id: " + pi)
                        )
        );

        // Step 1: Get existing assignments
        List<EmployeePackageAssignment> existing =
                assignmentRepository.findByEmployeeId(employeeId);

        Set<Long> newIds = new HashSet<>(packageIds);

        // Step 2: Delete assignments not in newIds
        List<EmployeePackageAssignment> toDelete = existing.stream()
                .filter(a -> !newIds.contains(a.getEmploymentPackage().getId()))
                .toList();

        assignmentRepository.deleteAll(toDelete);

        // Step 3: Identify packages to insert (new)
        Set<Long> existingIds = existing.stream()
                .map(a -> a.getEmploymentPackage().getId())
                .collect(Collectors.toSet());

        List<Long> toInsert = newIds.stream()
                .filter(id -> !existingIds.contains(id))
                .toList();

        List<EmployeePackageAssignment> inserted = toInsert.stream()
                .map(pkgId -> EmployeePackageAssignment.builder()
                        .employeeId(employeeId)
                        .employmentPackage(packageRepository.getReferenceById(pkgId))
                        .build())
                .collect(Collectors.toList());

        assignmentRepository.saveAll(inserted);

        // Step 4: Fetch final updated assignments
        List<EmployeePackageAssignment> finalList =
                assignmentRepository.findByEmployeeId(employeeId);

        // Step 5: Convert to DTO
        return finalList.stream()
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
