package com.microservice_employee.application.port.in;

import com.microservice_employee.domain.model.Employee;
import java.util.List;

public interface CreateEmployeeCase {

    List<Employee> createAll(List<Employee> employees);
}