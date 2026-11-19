package com.microservice_employee.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Employee(
        Long idEmployee,
        String firstName,
        String secondName,
        String lastName,
        String motherLastName,
        Integer age,
        String gender,
        LocalDate birthDate,
        String position,
        LocalDateTime createdAt,
        Boolean active
) {
}