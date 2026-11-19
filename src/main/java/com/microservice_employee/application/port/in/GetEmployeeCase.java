package com.microservice_employee.application.port.in;

import com.microservice_employee.domain.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GetEmployeeCase {

    Page<Employee> getAll(Pageable pageable);

    Employee getById(Long id);

    Page<Employee> searchByName(String name, Pageable pageable);
}