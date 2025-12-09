package com.example.new_starter.config.mapper;

import com.example.new_starter.model.dto.AddressDTO;
import com.example.new_starter.model.dto.EmployeeDTO;
import com.example.new_starter.model.entity.Address;
import com.example.new_starter.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    // ---------------------
    // ENTITY → DTO
    // ---------------------
    EmployeeDTO toEmployeeDTO(Employee employee);

    Employee toEmployee(EmployeeDTO employeeDTO);

    AddressDTO toAddressDTO(Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employeeNo", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void updateEmployeeFromDTO(EmployeeDTO dto, @MappingTarget Employee employee);

    Address toAddress(AddressDTO dto);

    List<EmployeeDTO> toDto(List<Employee> employees);
}