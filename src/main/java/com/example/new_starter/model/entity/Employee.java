package com.example.new_starter.model.entity;

import com.example.new_starter.config.SystemAddressUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "employee",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "employee_no")
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_no", nullable = false, unique = true)
    private String employeeNo;

    private String title;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String surname;

    private LocalDate dob;

    private String gender;

    private LocalDate startDate;

    // Created timestamp (set on creation)
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private String createdBy;

    // Updated timestamp (set on update)
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private String updatedBy;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private Address address;

    // Optional: Use lifecycle callback to set createdBy and updatedBy
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();  // Fallback in case @CreatedDate fails
        }

        this.setUpdatedAt(LocalDateTime.now());// Fallback in case @LastModifiedDate fails
        this.createdBy = getCurrentUser();  // Set the user who created the record
        this.updatedBy = getCurrentUser();
    }

    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();  // Fallback if @LastModifiedDate fails
        }
        this.updatedBy = getCurrentUser();  // Set the user who updated the record
    }

    private String getCurrentUser() {
        // Use actual user context or system info to set the user
        return SystemAddressUtil.getSystemAddress();// this is change for user id if user login flow is integrated.
    }

}
