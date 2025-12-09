# New Starter API -- Employee Management System

## Overview

The **New Starter API** is a Spring Boot--based Employee Management
System that provides full **CRUD operations** for employees and
comprehensive management of their associated **employment packages**.
The API is fully documented with Swagger (OpenAPI 3.1) and includes a
clean architecture with controllers, services, repositories, DTOs, and
Flyway migrations.

## Table of Contents

1.  Features
2.  API Endpoints
3.  How to Run the Project Locally
4.  Testing With Swagger
5.  Database & Flyway Migrations
6.  Unit Testing
7.  Project Structure
8.  Dependencies
9.  Conclusion
10. License

## Features

### Employee Management (CRUD)

-   Create, read, update, and delete employees.
-   RESTful endpoints with validation and proper error handling.

### Employment Package Management

-   Create and update employment packages.
-   Assign packages to employees.
-   Retrieve employee-specific packages.

### Swagger API Documentation

-   Auto-generated, interactive API docs using Springdoc OpenAPI.
-   Accessible through Swagger UI.

## API Endpoints

### Employee Management

  Method   Endpoint                Description
  -------- ----------------------- -------------------------------
  GET      `/api/employees`        Retrieve all employee records
  POST     `/api/employees`        Create a new employee
  GET      `/api/employees/{id}`   Retrieve employee by ID
  PUT      `/api/employees/{id}`   Update existing employee
  DELETE   `/api/employees/{id}`   Delete employee by ID

### Employment Package Management

  -------------------------------------------------------------------------------
  Method             Endpoint                       Description
  ------------------ ------------------------------ -----------------------------
  GET                `/api/packages`                Retrieve all employment
                                                    packages

  POST               `/api/packages/package`        Create a new package

  GET                `/api/packages/{employeeId}`   Get all packages for an
                                                    employee

  POST               `/api/packages/{employeeId}`   Assign packages to an
                                                    employee

  PUT                `/api/packages/package/{id}`   Update a package

  DELETE             `/api/packages/package/{id}`   Delete a package
  -------------------------------------------------------------------------------

## How to Run the Project Locally

### Prerequisites

-   Java 21+
-   Maven 3.8+
-   JDK 21
-   H2 (default), PostgreSQL (optional)

### 1. Clone the Repository

``` bash
git clone https://github.com/firewire01/onehub_newstarter.git
cd onehub_newstarter

```

### 2. Build the Project

``` bash
mvn clean install
```

### 3. Run the Application

``` bash
mvn spring-boot:run
```

### 4. Access Swagger API Documentation

-   Swagger UI: http://localhost:8080/swagger-ui/index.html
-   OpenAPI Docs: http://localhost:8080/v3/api-docs

## Testing With Swagger

1.  Start the application.
2.  Open Swagger UI.
3.  Execute API requests interactively.

## Database & Flyway Migrations

Default: H2\
To use PostgreSQL, update `application.yml`:

``` yml
spring:
  application:
    name: new-starter

  datasource:
    url: jdbc:h2:mem:employees;DB_CLOSE_DELAY=-1;MODE=PostgreSQL
    driverClassName: org.h2.Driver
    username: sa
    password: ''

  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: none

  h2:
    console:
      enabled: true
      path: /h2-console
```

## Unit Testing

Run:

``` bash
mvn test
```

## Project Structure

    com.example.newstarter
    ├── controller
    ├── model
    ├── dto
    ├── repository
    ├── service
    ├── exception
    ├── config
    └── NewStarterApplication.java

## Dependencies

-   Spring Boot
-   Spring Data JPA
-   Flyway
-   MapStruct
-   Springdoc OpenAPI / Swagger
-   JUnit 5

## Conclusion

The New Starter API is a complete Employee Management System with CRUD
operations, package management, Flyway migrations, Swagger
documentation, and unit testing.
