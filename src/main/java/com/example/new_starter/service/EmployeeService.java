package com.example.new_starter.service;

import com.example.new_starter.config.SystemAddressUtil;
import com.example.new_starter.config.mapper.EmployeeMapper;
import com.example.new_starter.model.dto.EmployeeDTO;
import com.example.new_starter.model.entity.Employee;
import com.example.new_starter.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public static final String PRE_PHASE = "EMP-";

    @Transactional
    public EmployeeDTO create(EmployeeDTO dto) {

        repository.findByEmail(dto.getEmail())
            .ifPresent(e -> { throw new IllegalArgumentException("Email already exists: " + dto.getEmail()); });

        // Create the employee from DTO
        Employee employee = mapper.toEmployee(dto);

        // Generate the employeeNo before saving (since the ID isn't assigned yet)
        String generatedEmployeeNo = "TEMP-" + LocalDate.now().getYear() + UUID.randomUUID();
        employee.setEmployeeNo(generatedEmployeeNo);

        // Save the entity
        Employee savedEmployee = repository.save(employee);

        // Now that the ID is set, generate the correct employeeNo
        savedEmployee.setEmployeeNo(String.format("%s%d%d", PRE_PHASE, LocalDate.now().getYear(), savedEmployee.getId()));

        // Save again after generating the final employeeNo
        return mapper.toEmployeeDTO(repository.save(savedEmployee));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        return mapper.toDto(employees);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        return mapper.toEmployeeDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        // You need the entity, not the DTO
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        // MapStruct merges DTO fields into entity
        mapper.updateEmployeeFromDTO(dto, existing);

        // Save the updated entity
        Employee updated = repository.save(existing);

        // Return DTO
        return mapper.toEmployeeDTO(updated);
    }

    public void deleteEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Employee not found with id " + id);
        }
        repository.deleteById(id);
    }


}