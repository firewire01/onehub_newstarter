package com.example.new_starter.controller;

import com.example.new_starter.model.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createAndGetEmployee_success() {
        // Create employee
        EmployeeDTO dto = EmployeeDTO.builder()
                .firstName("Alice")
                .surname("Smith")
                .email("alice.smith@example.com")
                .title("HR Manager")
                .startDate(LocalDate.now())
                .build();

        ResponseEntity<EmployeeDTO> createResponse = restTemplate.postForEntity(
                "/api/v1/employees", dto, EmployeeDTO.class);

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());
        Long id = createResponse.getBody().getId();
        assertNotNull(id);

        // Get employee
        ResponseEntity<EmployeeDTO> getResponse = restTemplate.getForEntity(
                "/api/v1/employees/" + id, EmployeeDTO.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Alice", getResponse.getBody().getFirstName());
    }

    @Test
    void createEmployee_duplicateEmail_returnsError() {
        EmployeeDTO dto = EmployeeDTO.builder()
                .firstName("Bob")
                .surname("Jones")
                .email("bob.jones@example.com")
                .title("Developer")
                .startDate(LocalDate.now())
                .build();

        restTemplate.postForEntity("/api/v1/employees", dto, EmployeeDTO.class);

        // Try to create again with same email
        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/v1/employees", dto, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Email already exists"));
    }
}
