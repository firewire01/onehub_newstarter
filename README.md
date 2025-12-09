New Starter API - Employee Management System
Overview

The New Starter API is a Spring Boot application designed to manage employee records and their associated employment packages. The API provides full CRUD functionality for managing employee data, along with endpoints for assigning and managing employment packages.

This API includes the following features:

Employee Management: Create, read, update, and delete employee records.

Employment Package Management: Assign and manage employment packages for employees.

Swagger API Documentation: All API endpoints are fully documented and accessible through Swagger UI.

Features
Employee Management CRUD APIs

GET /api/employees: Retrieve all employees.

POST /api/employees: Create a new employee.

GET /api/employees/{id}: Retrieve an employee by ID.

PUT /api/employees/{id}: Update an existing employee.

DELETE /api/employees/{id}: Delete an employee by ID.

Employment Package Management APIs

GET /api/packages: Get all employment packages.

POST /api/packages/package: Create a new employment package.

GET /api/packages/{employeeId}: Retrieve all employment packages assigned to an employee.

POST /api/packages/{employeeId}: Assign employment packages to an employee.

PUT /api/packages/package/{id}: Update an employment package.

DELETE /api/packages/package/{id}: Delete an employment package.

How to Run the Project Locally
Prerequisites

Before running the project, make sure you have the following installed:

Java 21 (or higher)

Maven 3.8+

JDK 21 installed

Database: By default, the project uses H2 in-memory database. You can configure it to use other databases like PostgreSQL by modifying the application.properties file.

1. Clone the Repository

First, clone the repository to your local machine:

git clone https://github.com/your-username/new-starter.git
cd new-starter

2. Build the Project

Next, build the project using Maven:

mvn clean install


This will download all the necessary dependencies and build the application.

3. Run the Application

Run the application locally with the following command:

mvn spring-boot:run


By default, the application will start on port 8080.

4. Access the API Documentation (Swagger UI)

Once the application is running, you can access the Swagger UI for testing the APIs.

Swagger UI: http://localhost:8080/swagger-ui/index.html

This will provide an interactive UI to test all the available API endpoints.

API Endpoints
New Starter API (OAS 3.1)

You can access the API documentation for all available endpoints in OpenAPI format here:

OpenAPI Docs: http://localhost:8080/v3/api-docs

Employee Management CRUD APIs

GET /api/employees: Retrieves all employee records.

POST /api/employees: Creates a new employee.

GET /api/employees/{id}: Retrieves an employee by their ID.

PUT /api/employees/{id}: Updates an employee record.

DELETE /api/employees/{id}: Deletes an employee by their ID.

Employment Package APIs

GET /api/packages: Retrieves all employment packages.

POST /api/packages/package: Creates a new employment package.

GET /api/packages/{employeeId}: Retrieves all employment packages assigned to a specific employee.

POST /api/packages/{employeeId}: Assigns employment packages to an employee.

PUT /api/packages/package/{id}: Updates an employment package.

DELETE /api/packages/package/{id}: Deletes an employment package.

How to Test the API Using Swagger

Start the application using the mvn spring-boot:run command.

Open Swagger UI in your browser at: http://localhost:8080/swagger-ui/index.html
.

Explore the API: Swagger UI will show you all the available API endpoints, their parameters, and request/response formats.

Execute API requests: You can execute the API requests directly from the Swagger UI. Just click on any API method (GET, POST, PUT, DELETE), fill in the necessary parameters, and click Execute.

Database Setup and Flyway Migrations

The application uses Flyway for database migrations. By default, it uses H2 Database in memory. If you wish to switch to another database (e.g., PostgreSQL), you need to update the database connection settings in the application.properties file.

Example configuration for PostgreSQL:

spring.datasource.url=jdbc:postgresql://localhost:5432/lamhoa_db
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update


Flyway will automatically apply any pending migration scripts on startup.

Unit Testing

To run the unit tests for the project, use the following Maven command:

mvn test


The project is pre-configured with unit tests for the EmployeeController and EmployeeService. These tests ensure that the business logic and API endpoints work correctly.

Project Structure

Here’s a breakdown of the project structure:

com.example.newstarter:

Main application class and configuration files.

com.example.newstarter.controller:

REST controllers for handling employee and package management endpoints.

com.example.newstarter.model:

Domain models such as Employee, Package.

com.example.newstarter.dto:

Data Transfer Objects (DTOs) for communication between client and server.

com.example.newstarter.repository:

JPA repositories for CRUD operations.

com.example.newstarter.service:

Service layer for business logic.

com.example.newstarter.exception:

Global exception handling classes.

com.example.newstarter.config:

Configuration for Swagger, Flyway, and other application settings.

Dependencies

This project includes several key dependencies:

Spring Boot for building the application.

Flyway for managing database migrations.

Spring Data JPA for interacting with the database.

MapStruct for DTO to entity mapping.

Swagger (Springdoc OpenAPI) for API documentation and testing.

JUnit 5 for unit testing.