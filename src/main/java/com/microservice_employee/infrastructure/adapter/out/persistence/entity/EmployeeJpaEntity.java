package com.microservice_employee.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPLOYEE")
    private Long idEmployee;

    @Column(name = "FIRST_NAME", nullable = false, length = 60)
    private String firstName;

    @Column(name = "SECOND_NAME", length = 60)
    private String secondName;

    @Column(name = "LAST_NAME", nullable = false, length = 60)
    private String lastName;

    @Column(name = "MOTHER_LAST_NAME", nullable = false, length = 60)
    private String motherLastName;

    @Column(name = "AGE", precision = 3, scale = 0)
    private Integer age;

    @Column(name = "GENDER", nullable = false, length = 1, columnDefinition = "CHAR(1)")
    private String gender;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Column(name = "POSITION", nullable = false, length = 100)
    private String position;

    @Generated(event = EventType.INSERT)
    @Column(name = "CREATED_AT", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;
}