package com.microservice_employee.application.port.out;

import com.microservice_employee.domain.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Page<Employee> findAll(Pageable pageable);

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);

    List<Employee> saveAll(List<Employee> employees);

    void deleteById(Long id);

    Page<Employee> searchByName(String name, Pageable pageable);
}
