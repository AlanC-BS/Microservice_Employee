package com.microservice_employee.infrastructure.adapter.in.rest.exception;

import com.microservice_employee.application.exception.EmployeeNotFoundException;
import com.microservice_employee.application.exception.InvalidEmployeeException;
import com.microservice_employee.infrastructure.adapter.in.rest.constant.ErrorMessages;
import com.microservice_employee.infrastructure.adapter.in.rest.constant.LogMessages;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EmployeeNotFoundException ex, HttpServletRequest request) {

        log.warn(
                LogMessages.EMPLOYEE_NOT_FOUND,
                request.getRequestURI(),
                ex.getMessage()
        );

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ErrorMessages.EMPLOYEE_NOT_FOUND,
                request,
                List.of()
        );
    }

    @ExceptionHandler(InvalidEmployeeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmployee(
            InvalidEmployeeException ex,
            HttpServletRequest request) {

        log.warn(
                LogMessages.INVALID_EMPLOYEE,
                request.getRequestURI(),
                ex.getMessage()
        );

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.INVALID_EMPLOYEE,
                request,
                List.of()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage()
                )
                .toList();

        log.warn(
                LogMessages.VALIDATION_ERROR,
                request.getRequestURI(),
                details
        );

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.VALIDATION_ERROR,
                request,
                details
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {

        List<String> details = ex.getConstraintViolations()
                .stream()
                .map(violation ->
                        violation.getPropertyPath() + ": " +
                                violation.getMessage()
                )
                .toList();

        log.warn(
                LogMessages.CONSTRAINT_VIOLATION,
                request.getRequestURI(),
                details
        );

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.CONSTRAINT_VIOLATION,
                request,
                details
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(HttpMessageNotReadableException ex, HttpServletRequest request) {

        Throwable cause = ex.getMostSpecificCause();

        log.warn(
                LogMessages.INVALID_JSON,
                request.getRequestURI(),
                cause.getMessage()
        );

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.INVALID_JSON,
                request,
                List.of()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        log.warn(
                LogMessages.TYPE_MISMATCH,
                request.getRequestURI(),
                ex.getName(),
                ex.getValue()
        );

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.INVALID_PARAMETER_TYPE.formatted(ex.getName()),
                request,
                List.of()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {

        log.error(
                LogMessages.DATA_INTEGRITY_ERROR,
                request.getRequestURI(),
                ex
        );

        return buildResponse(
                HttpStatus.CONFLICT,
                ErrorMessages.DATA_INTEGRITY_ERROR,
                request,
                List.of()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, HttpServletRequest request) {

        log.error(
                LogMessages.UNEXPECTED_ERROR,
                request.getRequestURI(),
                ex
        );

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorMessages.INTERNAL_SERVER_ERROR,
                request,
                List.of()
        );
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, HttpServletRequest request,
            List<String> details) {

        ErrorResponse response = new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                details
        );

        return ResponseEntity.status(status).body(response);
    }
}