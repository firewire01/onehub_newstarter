# New Starter API - Employee Management System

## Overview

The **New Starter API** is a Spring Boot application designed to manage employee records and their associated employment packages. The API provides full CRUD functionality for managing employee data, along with endpoints for assigning and managing employment packages.

This API includes the following features:
- **Employee Management**: Create, read, update, and delete employee records.
- **Employment Package Management**: Assign and manage employment packages for employees.
- **Swagger API Documentation**: All API endpoints are fully documented and accessible through Swagger UI.

---

## Features

### Employee Management CRUD APIs

- **GET /api/employees**: Retrieve all employees.
- **POST /api/employees**: Create a new employee.
- **GET /api/employees/{id}**: Retrieve an employee by ID.
- **PUT /api/employees/{id}**: Update an existing employee.
- **DELETE /api/employees/{id}**: Delete an employee by ID.

### Employment Package Management APIs

- **GET /api/packages**: Get all employment packages.
- **POST /api/packages/package**: Create a new employment package.
- **GET /api/packages/{employeeId}**: Retrieve all employment packages assigned to an employee.
- **POST /api/packages/{employeeId}**: Assign employment packages to an employee.
- **PUT /api/packages/package/{id}**: Update an employment package.
- **DELETE /api/packages/package/{id}**: Delete an employment package.

---

## How to Run the Project Locally

### Prerequisites

Before running the project, make sure you have the following installed:
- **Java 21** (or higher) 
- **Maven 3.8+**
- **JDK 21** installed
- **Database**: By default, the project uses **H2 in-memory database**. You can configure it to use other databases like **PostgreSQL** by modifying the `application.yml` file.

### 1. Clone the Repository

First, clone the repository to your local machine:

```bash
git clone https://github.com/firewire01/onehub_newstarter.git
cd onehub_newstarter
```


### Project Structure

Here’s a breakdown of the project structure:
- **com.example.newstarter:
- ***Main application class and configuration files.
- **com.example.newstarter.controller:
- ***REST controllers for handling employee and package management endpoints.
- **com.example.newstarter.model:
- ***Domain models such as Employee, Package.
- **com.example.newstarter.dto:
- ***Data Transfer Objects (DTOs) for communication between client and server.
- **com.example.newstarter.repository:
- ***JPA repositories for CRUD operations.
- **com.example.newstarter.service:
- ***Service layer for business logic.
- **com.example.newstarter.exception:
- ***Global exception handling classes.
- **com.example.newstarter.config:
- ***Configuration for Swagger, Flyway, and other application settings.

### Dependencies

- **This project includes several key dependencies:
- **Spring Boot for building the application.
- **Flyway for managing database migrations.
- **Spring Data JPA for interacting with the database.
- **MapStruct for DTO to entity mapping.
- **Swagger (Springdoc OpenAPI) for API documentation and testing.
- **JUnit 5 for unit testing.
