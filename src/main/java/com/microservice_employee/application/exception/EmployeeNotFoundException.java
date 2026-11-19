package com.microservice_employee.application.exception;

import com.microservice_employee.application.constant.EmployeeMessages;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super(EmployeeMessages.EMPLOYEE_NOT_FOUND.formatted(id));
    }
}