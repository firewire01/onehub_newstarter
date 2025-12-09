package com.example.new_starter.service;

import com.example.new_starter.config.mapper.EmployeeMapper;
import com.example.new_starter.model.dto.EmployeeDTO;
import com.example.new_starter.model.entity.Employee;
import com.example.new_starter.repo.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeMapper mapper;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .title("Developer")
                .startDate(LocalDate.now())
                .build();

        employeeDTO = EmployeeDTO.builder()
                .id(1L)
                .firstName("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .title("Developer")
                .startDate(LocalDate.now())
                .build();
    }

    @Test
    void addNewStarter_success() {
        when(repository.findByEmail(employeeDTO.getEmail())).thenReturn(Optional.empty());
        when(mapper.toEmployee(employeeDTO)).thenReturn(employee);
        when(repository.save(employee)).thenReturn(employee);
        when(mapper.toEmployeeDTO(employee)).thenReturn(employeeDTO);

        EmployeeDTO result = service.create(employeeDTO);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());

        verify(repository, times(2)).save(employee);
    }

    @Test
    void addNewStarter_emailExists_throwsException() {
        when(repository.findByEmail(employeeDTO.getEmail())).thenReturn(Optional.of(employee));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.create(employeeDTO));

        assertEquals("Email already exists: john.doe@example.com", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void getById_success() {
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        when(mapper.toEmployeeDTO(employee)).thenReturn(employeeDTO);

        EmployeeDTO result = service.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void getById_notFound_throwsException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getEmployeeById(1L));
        assertEquals("Employee not found with id: 1", exception.getMessage());
    }

    @Test
    void updateEmployee_success() {
        EmployeeDTO updateDTO = EmployeeDTO.builder()
                .firstName("Jane")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        doAnswer(invocation -> {
            EmployeeDTO dto = invocation.getArgument(0);
            Employee e = invocation.getArgument(1);
            if (dto.getFirstName() != null) e.setFirstName(dto.getFirstName());
            return null;
        }).when(mapper).updateEmployeeFromDTO(updateDTO, employee);
        when(repository.save(employee)).thenReturn(employee);
        when(mapper.toEmployeeDTO(employee)).thenReturn(EmployeeDTO.builder()
                .id(1L)
                .firstName("Jane")
                .surname("Doe")
                .email("john.doe@example.com")
                .build());

        EmployeeDTO result = service.updateEmployee(1L, updateDTO);

        assertEquals("Jane", result.getFirstName());
        verify(repository).save(employee);
    }
}
