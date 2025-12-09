package com.example.new_starter.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String employeeNo;

    private String title;

    @NotBlank
    private String firstName;

    @NotBlank
    private String surname;

    private LocalDate dob;

    private String gender;

    private LocalDate startDate;

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnore
    private String updatedBy;

    @Email
    @NotBlank
    private String email;

    @Valid
    private AddressDTO address;

    @JsonProperty
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonProperty
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty
    public String getUpdatedBy() {
        return updatedBy;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getEmployeeNo() {
        return employeeNo;
    }
}
