package com.microservice_employee.application.exception;

public class InvalidEmployeeException extends RuntimeException {
    public InvalidEmployeeException(String message) {
        super(message);
    }
}