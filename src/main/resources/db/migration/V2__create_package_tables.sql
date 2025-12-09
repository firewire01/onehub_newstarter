-- Create employment_package table
CREATE TABLE employment_package (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Create table for employee package assignments
CREATE TABLE employee_package_assignment (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    package_id BIGINT NOT NULL,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(id),
    CONSTRAINT fk_package FOREIGN KEY (package_id) REFERENCES employment_package(id) ON DELETE CASCADE
);
