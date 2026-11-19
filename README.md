
# Microservice Employee

RESTful API for employee management, developed with Java and Spring Boot using Hexagonal Architecture.

## Overview

Microservice Employee is a REST API designed to manage employee information.

The application provides endpoints to create, retrieve, update, delete, and search employees.

## Technologies

| Technology | Version |
|---|---|
| Java | 25 |
| Spring Boot | 4.1.2 |
| Maven | Maven Wrapper |
| Oracle AI Database | 26ai Free |
| Spring Data JPA | Spring Boot managed |
| Spring Validation | Spring Boot managed |
| Springdoc OpenAPI | 3.2.0 |
| JUnit 5 | Spring Boot managed |
| Mockito | 5.23.0 |


## Features

- Create one or multiple employees.
- Retrieve employees with pagination.
- Retrieve an employee by ID.
- Update employee information.
- Delete employees.
- Search employees by name.
- Request validation.
- Centralized exception handling.
- Standardized API responses.
- OpenAPI / Swagger documentation.
- Unit tests and controller tests.

## API Endpoints

Base URL:
http://localhost:8080/api/v1/employees


| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/employees` | Create employees |
| GET | `/api/v1/employees` | Retrieve employees with pagination |
| GET | `/api/v1/employees/{id}` | Retrieve employee by ID |
| PUT | `/api/v1/employees/{id}` | Update employee |
| DELETE | `/api/v1/employees/{id}` | Delete employee |
| GET | `/api/v1/employees/search?name={name}` | Search employees by name |


