CREATE TABLE employee (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    employee_no VARCHAR(100) NOT NULL UNIQUE,
    title VARCHAR(20),
    first_name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    dob DATE,
    gender VARCHAR(20),
    email VARCHAR(255) NOT NULL UNIQUE,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255),
    postal_code VARCHAR(20),
    start_date DATE,

    -- Created and updated timestamps
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255)
);

