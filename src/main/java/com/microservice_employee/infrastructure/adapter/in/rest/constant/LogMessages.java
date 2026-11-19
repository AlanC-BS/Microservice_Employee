package com.microservice_employee.infrastructure.adapter.in.rest.constant;

public final class LogMessages {

    private LogMessages() {
    }

    public static final String EMPLOYEE_NOT_FOUND = "Employee not found. URI: {}, Message: {}";
    public static final String INVALID_EMPLOYEE = "Invalid employee request. URI: {}, Message: {}";
    public static final String VALIDATION_ERROR = "Request validation failed. URI: {}, Details: {}";
    public static final String CONSTRAINT_VIOLATION = "Constraint violation detected. URI: {}, Details: {}";
    public static final String INVALID_JSON = "Invalid request body. URI: {}, Cause: {}";
    public static final String TYPE_MISMATCH = "Request parameter type mismatch. URI: {}, Parameter: {}, Received value: {}";
    public static final String DATA_INTEGRITY_ERROR = "The operation cannot be completed due to a data restriction. URI: {}";
    public static final String UNEXPECTED_ERROR = "Unexpected error while processing request. URI: {}";
}