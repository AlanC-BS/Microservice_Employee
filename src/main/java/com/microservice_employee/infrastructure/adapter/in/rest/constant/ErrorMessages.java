package com.microservice_employee.infrastructure.adapter.in.rest.constant;

public final class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String INVALID_EMPLOYEE = "The employee information is not valid";
    public static final String VALIDATION_ERROR = "The submitted data is not valid";
    public static final String CONSTRAINT_VIOLATION = "The parameters sent are not valid";
    public static final String INVALID_JSON = "The request body contains invalid JSON";
    public static final String INVALID_PARAMETER_TYPE = "The parameter '%s' has an invalid data type";
    public static final String DATA_INTEGRITY_ERROR = "The operation could not be completed due to a data restriction";
    public static final String INTERNAL_SERVER_ERROR = "An unexpected error occurred while processing the request";
}
