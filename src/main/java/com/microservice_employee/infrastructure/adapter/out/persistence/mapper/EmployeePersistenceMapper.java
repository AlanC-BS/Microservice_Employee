package com.microservice_employee.infrastructure.adapter.out.persistence.mapper;

import com.microservice_employee.domain.model.Employee;
import com.microservice_employee.infrastructure.adapter.out.persistence.entity.EmployeeJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeePersistenceMapper {

    public Employee toDomain(EmployeeJpaEntity entity) {
        return new Employee(
                entity.getIdEmployee(),
                entity.getFirstName(),
                entity.getSecondName(),
                entity.getLastName(),
                entity.getMotherLastName(),
                entity.getAge(),
                entity.getGender(),
                entity.getBirthDate(),
                entity.getPosition(),
                entity.getCreatedAt(),
                entity.getActive()
        );
    }

    public EmployeeJpaEntity toEntity(Employee employee) {
        EmployeeJpaEntity entity = new EmployeeJpaEntity();

        entity.setIdEmployee(employee.idEmployee());
        entity.setFirstName(employee.firstName());
        entity.setSecondName(employee.secondName());
        entity.setLastName(employee.lastName());
        entity.setMotherLastName(employee.motherLastName());
        entity.setAge(employee.age());
        entity.setGender(employee.gender());
        entity.setBirthDate(employee.birthDate());
        entity.setPosition(employee.position());
        entity.setCreatedAt(employee.createdAt());
        entity.setActive(employee.active());

        return entity;
    }
}
