package com.microservice_employee.application.port.in;

import com.microservice_employee.domain.model.Employee;

public interface UpdateEmployeeCase {

    Employee update(Long id, Employee employee);
}