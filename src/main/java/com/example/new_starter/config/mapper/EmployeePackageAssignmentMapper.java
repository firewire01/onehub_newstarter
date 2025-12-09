package com.example.new_starter.config.mapper;


import com.example.new_starter.model.dto.EmployeePackageAssignmentDTO;
import com.example.new_starter.model.dto.EmploymentPackageDTO;
import com.example.new_starter.model.entity.EmployeePackageAssignment;
import com.example.new_starter.model.entity.EmploymentPackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeePackageAssignmentMapper {

    EmployeePackageAssignmentDTO toDto(EmployeePackageAssignment entity);

    EmploymentPackage employeePackageToEntity(EmploymentPackageDTO packageDTO);

    EmploymentPackageDTO employeePackageToDto(EmploymentPackage employmentPackage);

    @Mapping(target = "id", ignore = true)
    void updateEmployeePackage(EmploymentPackageDTO dto, @MappingTarget EmploymentPackage employee);
}
