
package com.microservice_employee.application.service;

import com.microservice_employee.application.exception.EmployeeNotFoundException;
import com.microservice_employee.application.exception.InvalidEmployeeException;
import com.microservice_employee.application.port.out.EmployeeRepository;
import com.microservice_employee.domain.model.Employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployeeSuccess() {

        Employee employee = createEmployee(1L, "Carlos");
        List<Employee> employees = List.of(employee);

        when(employeeRepository.saveAll(employees))
                .thenReturn(employees);

        List<Employee> result = employeeService.createAll(employees);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Carlos", result.getFirst().firstName());

        verify(employeeRepository, times(1)).saveAll(employees);
    }

    @Test
    void exceptionWhenEmployeeIdDoesNotExist() {

        Long employeeId = 100L;

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getById(employeeId)
        );

        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void getEmployeeWhenIdExists() {

        Long employeeId = 1L;
        Employee employee = createEmployee(employeeId, "Saul");

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getById(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.idEmployee());
        assertEquals("Saul", result.firstName());

        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void exceptionWhenEmployeeListIsNull() {

        assertThrows(
                InvalidEmployeeException.class,
                () -> employeeService.createAll(null)
        );

        verify(employeeRepository, never()).saveAll(anyList());
    }

    private Employee createEmployee(Long id, String firstName) {
        return new Employee(
                id,
                firstName,
                "Antonio",
                "Ramirez",
                "Lopez",
                30,
                "M",
                LocalDate.of(1996, 5, 10),
                "Software Developer",
                LocalDateTime.of(2026, 1, 15, 10, 30, 0),
                true
        );
    }
}
