package com.microservice_employee.application.service;


import static com.microservice_employee.application.constant.EmployeeMessages.*;

import com.microservice_employee.application.constant.EmployeeMessages;
import com.microservice_employee.application.exception.EmployeeNotFoundException;
import com.microservice_employee.application.exception.InvalidEmployeeException;
import com.microservice_employee.application.port.in.*;
import com.microservice_employee.application.port.out.EmployeeRepository;
import com.microservice_employee.application.util.UpdateUtils;
import com.microservice_employee.domain.model.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService implements  CreateEmployeeCase, GetEmployeeCase, UpdateEmployeeCase,
        DeleteEmployeeCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> createAll(List<Employee> employees) {

        if (employees == null || employees.isEmpty()) {
            throw new InvalidEmployeeException(EMPLOYEE_LIST_EMPTY);
        }

        return employeeRepository.saveAll(employees);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> searchByName(String name, Pageable pageable) {

        if (name == null || name.isBlank()) {
            throw new InvalidEmployeeException(EMPLOYEE_SEARCH_NAME_REQUIRED);
        }

        return employeeRepository.searchByName(name.trim(), pageable);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        Employee updated = new Employee(
                existing.idEmployee(),
                UpdateUtils.valueOrDefault(employee.firstName(), existing.firstName()),
                UpdateUtils.valueOrDefault(employee.secondName(),existing.secondName()),
                UpdateUtils.valueOrDefault(employee.lastName(),existing.lastName()),
                UpdateUtils.valueOrDefault(employee.motherLastName(),existing.motherLastName()),
                UpdateUtils.valueOrDefault(employee.age(),existing.age()),
                UpdateUtils.valueOrDefault(employee.gender(),existing.gender()),
                UpdateUtils.valueOrDefault(employee.birthDate(),existing.birthDate()),
                UpdateUtils.valueOrDefault(employee.position(),existing.position()),
                existing.createdAt(),
                UpdateUtils.valueOrDefault(employee.active(), existing.active())
        );

        return employeeRepository.save(updated);
    }

    @Override
    public void delete(Long id) {

        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.deleteById(id);
    }
}