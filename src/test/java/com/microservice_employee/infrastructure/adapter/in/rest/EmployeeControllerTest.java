
package com.microservice_employee.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.microservice_employee.application.exception.EmployeeNotFoundException;
import com.microservice_employee.application.port.in.CreateEmployeeCase;
import com.microservice_employee.application.port.in.DeleteEmployeeCase;
import com.microservice_employee.application.port.in.GetEmployeeCase;
import com.microservice_employee.application.port.in.UpdateEmployeeCase;
import com.microservice_employee.domain.model.Employee;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {

    private static final String BASE_URL = "/api/v1/employees";

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateEmployeeCase createEmployeeCase;

    @MockitoBean
    private GetEmployeeCase getEmployeeCase;

    @MockitoBean
    private UpdateEmployeeCase updateEmployeeCase;

    @MockitoBean
    private DeleteEmployeeCase deleteEmployeeCase;

    @Test
    void createEmployeeSuccess() throws Exception {

        Employee employee = createEmployee(1L, "Jimena");

        when(createEmployeeCase.createAll(anyList()))
                .thenReturn(List.of(employee));

        String requestBody = """
                [
                    {
                        "firstName": "Jimena",
                        "secondName": "Karla",
                        "lastName": "Ubilla",
                        "motherLastName": "Gonzalez",
                        "age": 26,
                        "gender": "F",
                        "birthDate": "19-09-2000",
                        "position": "Project Manager",
                        "active": true
                    }
                ]
                """;

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].firstName").value("Jimena"));

        verify(createEmployeeCase).createAll(anyList());
    }

    @Test
    void badRequestWhenEmployeeDataIsInvalid() throws Exception {

        String requestBody = """
                [
                    {
                        "firstName": "",
                        "lastName": "Ramirez",
                        "motherLastName": "Lopez",
                        "age": 30,
                        "gender": "M",
                        "birthDate": "1996-05-10",
                        "position": "Software Developer",
                        "active": true
                    }
                ]
                """;

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

        verify(createEmployeeCase, org.mockito.Mockito.never())
                .createAll(anyList());
    }


    @Test
    void getEmployeeWhenIdExists() throws Exception {

        Long employeeId = 1L;
        Employee employee = createEmployee(employeeId, "Susana");

        when(getEmployeeCase.getById(employeeId))
                .thenReturn(employee);

        mockMvc.perform(get(BASE_URL + "/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.firstName").value("Susana"));

        verify(getEmployeeCase).getById(employeeId);
    }

    @Test
    void shouldReturnNotFoundWhenEmployeeDoesNotExist() throws Exception {

        Long employeeId = 100L;

        when(getEmployeeCase.getById(employeeId))
                .thenThrow(new EmployeeNotFoundException(employeeId));

        mockMvc.perform(get(BASE_URL + "/{id}", employeeId))
                .andExpect(status().isNotFound());

        verify(getEmployeeCase).getById(employeeId);
    }

    @Test
    void internalServerErrorGetEmployeeById() throws Exception {

        Long employeeId = 1L;

        when(getEmployeeCase.getById(employeeId))
                .thenThrow(new IllegalStateException("Unexpected internal error"));

        mockMvc.perform(get(BASE_URL + "/{id}", employeeId))
                .andExpect(status().isInternalServerError());

        verify(getEmployeeCase).getById(employeeId);
    }

    private Employee createEmployee(Long id, String firstName) {

        return new Employee(
                id,
                firstName,
                "Karla",
                "Ubilla",
                "Gonzalez",
                26,
                "F",
                LocalDate.of(2000, 9, 19),
                "Project Manager",
                LocalDateTime.of(2026, 1, 2, 15, 45, 0),
                true
        );
    }
}
