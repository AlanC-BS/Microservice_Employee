package com.microservice_employee.infrastructure.adapter.in.rest;

import com.microservice_employee.application.port.in.*;
import com.microservice_employee.domain.model.Employee;
import com.microservice_employee.infrastructure.adapter.in.rest.constant.DocumentationMessages;
import com.microservice_employee.infrastructure.adapter.in.rest.constant.ResponseApiMessages;
import com.microservice_employee.infrastructure.adapter.in.rest.dto.ApiBodyResponse;
import com.microservice_employee.infrastructure.adapter.in.rest.dto.EmployeeRequest;
import com.microservice_employee.infrastructure.adapter.in.rest.dto.EmployeeResponse;
import com.microservice_employee.infrastructure.adapter.in.rest.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = DocumentationMessages.EMPLOYEE_TAG, description = DocumentationMessages.DESCRIPTION_TAG)
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final CreateEmployeeCase createEmployeeCase;
    private final GetEmployeeCase getEmployeeCase;
    private final UpdateEmployeeCase updateEmployeeCase;
    private final DeleteEmployeeCase deleteEmployeeCase;

    public EmployeeController(
            CreateEmployeeCase createEmployeeCase,
            GetEmployeeCase getEmployeeCase,
            UpdateEmployeeCase updateEmployeeCase,
            DeleteEmployeeCase deleteEmployeeCase) {

        this.createEmployeeCase = createEmployeeCase;
        this.getEmployeeCase = getEmployeeCase;
        this.updateEmployeeCase = updateEmployeeCase;
        this.deleteEmployeeCase = deleteEmployeeCase;
    }

    @Operation(
            summary = DocumentationMessages.GET_ALL,
            description = DocumentationMessages.DESC_GET_ALL
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_OK,
                    description = DocumentationMessages.OK_MESSAGE_GET_ALL
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_INT_SER_ERROR,
                    description = DocumentationMessages.INT_SER_ERROR_MESSAGE
            )
    })
    @GetMapping
    public ResponseEntity<ApiBodyResponse<Page<EmployeeResponse>>> getAll(
            @PageableDefault(
                    size = 10,
                    sort = "idEmployee",
                    direction = Sort.Direction.ASC
            ) Pageable pageable, HttpServletRequest request) {

        Page<EmployeeResponse> result = getEmployeeCase
                .getAll(pageable)
                .map(this::toResponse);

        return ResponseEntity.ok(
                ApiBodyResponse.success(
                        HttpStatus.OK,
                        ResponseApiMessages.EMPLOYEES_FOUND,
                        result,
                        request.getRequestURI()
                )
        );

    }

    @Operation(
            summary = DocumentationMessages.GET_BY_ID,
            description = DocumentationMessages.DESC_GET_BY_ID
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_OK,
                    description = DocumentationMessages.OK_MESSAGE_GET_BY_ID
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_NOT_FOUND,
                    description = DocumentationMessages.NOT_FOUND_MESSAGE_GET_BY_ID
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_INT_SER_ERROR,
                    description = DocumentationMessages.INT_SER_ERROR_MESSAGE
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiBodyResponse<EmployeeResponse>> getById(
            @Parameter(
                    description = DocumentationMessages.UNIQUE_ID_MESSAGE,
                    example = DocumentationMessages.ONE_VALUE,
                    required = true
            )
            @PathVariable Long id,
            HttpServletRequest request) {

        Employee employee = getEmployeeCase.getById(id);
        return ResponseEntity.ok(
                ApiBodyResponse.success(
                        HttpStatus.OK,
                        ResponseApiMessages.EMPLOYEE_FOUND,
                        toResponse(employee),
                        request.getRequestURI()
                )
        );
    }

    @Operation(
            summary = DocumentationMessages.CREATE,
            description = DocumentationMessages.DESC_CREATE
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_CREATED,
                    description = DocumentationMessages.OK_MESSAGE_CREATE
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_BAD_REQUEST,
                    description = DocumentationMessages.BAD_REQUEST_MESSAGE
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_INT_SER_ERROR,
                    description = DocumentationMessages.INT_SER_ERROR_MESSAGE
            )
    })
    @PostMapping
    public ResponseEntity<ApiBodyResponse<List<EmployeeResponse>>> createAll(@Valid @RequestBody List<@Valid EmployeeRequest> requests,
                                                                             HttpServletRequest request) {

        List<Employee> employees = requests.stream()
                .map(this::toDomain)
                .toList();

        List<EmployeeResponse> result = createEmployeeCase
                .createAll(employees)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiBodyResponse.success(
                            HttpStatus.CREATED,
                            result.size() > 1 ? ResponseApiMessages.EMPLOYEES_CREATED : ResponseApiMessages.EMPLOYEE_CREATED,
                            result,
                            request.getRequestURI()
                        )
                );
    }

    @Operation(
            summary = DocumentationMessages.UPDATE,
            description = DocumentationMessages.DESC_UPDATE
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_OK,
                    description = DocumentationMessages.OK_MESSAGE_UPDATE
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_BAD_REQUEST,
                    description = DocumentationMessages.BAD_REQUEST_MESSAGE
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_NOT_FOUND,
                    description = DocumentationMessages.NOT_FOUND_MESSAGE_UPDATE
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_INT_SER_ERROR,
                    description = DocumentationMessages.INT_SER_ERROR_MESSAGE
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiBodyResponse<EmployeeResponse>> update(
            @Parameter(
                    description = DocumentationMessages.UNIQUE_ID_MESSAGE,
                    example = DocumentationMessages.ONE_VALUE,
                    required = true
            )
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request,
            HttpServletRequest httpRequest) {

        Employee employee = toDomain(request);

        Employee updated = updateEmployeeCase.update(id, employee);

        return ResponseEntity.ok(
                ApiBodyResponse.success(
                        HttpStatus.OK,
                        ResponseApiMessages.EMPLOYEE_UPDATED,
                        toResponse(updated),
                        httpRequest.getRequestURI()
                )
        );
    }

    @Operation(
            summary = DocumentationMessages.DELETE,
            description = DocumentationMessages.DESC_DELETE
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_OK,
                    description = DocumentationMessages.OK_MESSAGE_DELETE
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_NOT_FOUND,
                    description = DocumentationMessages.NOT_FOUND_MESSAGE_UPDATE
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_INT_SER_ERROR,
                    description = DocumentationMessages.INT_SER_ERROR_MESSAGE
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiBodyResponse<Object>> delete(
            @Parameter(
                    description = DocumentationMessages.UNIQUE_ID_MESSAGE,
                    example = DocumentationMessages.ONE_VALUE,
                    required = true
            )
            @PathVariable Long id,
            HttpServletRequest request) {

        deleteEmployeeCase.delete(id);

        return ResponseEntity.ok(
                ApiBodyResponse.success(
                        HttpStatus.OK,
                        ResponseApiMessages.EMPLOYEE_DELETED,
                        null,
                        request.getRequestURI()
                )
        );
    }

    @Operation(
            summary = DocumentationMessages.SEARCH,
            description = DocumentationMessages.DESC_SEARCH
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_OK,
                    description = DocumentationMessages.OK_MESSAGE_SEARCH
            ),
            @ApiResponse(
                    responseCode = DocumentationMessages.CODE_INT_SER_ERROR,
                    description = DocumentationMessages.INT_SER_ERROR_MESSAGE
            )
    })
    @GetMapping("/search")
    public ResponseEntity<ApiBodyResponse<PageResponse<EmployeeResponse>>> searchByName(
            @RequestParam String name,
            @PageableDefault(
                    size = 10,
                    sort = "idEmployee",
                    direction = Sort.Direction.ASC
            ) Pageable pageable, HttpServletRequest request) {

        Page<EmployeeResponse> result = getEmployeeCase
                .searchByName(name, pageable)
                .map(this::toResponse);
        return ResponseEntity.ok(
                ApiBodyResponse.success(
                        HttpStatus.OK,
                        ResponseApiMessages.EMPLOYEE_SEARCH_COMPLETED,
                        PageResponse.from(result),
                        request.getRequestURI()
                )
        );
    }

    private Employee toDomain(EmployeeRequest request) {

        return new Employee(
                null,
                request.firstName(),
                request.secondName(),
                request.lastName(),
                request.motherLastName(),
                request.age(),
                request.gender(),
                request.birthDate(),
                request.position(),
                null,
                request.active()
        );
    }

    private EmployeeResponse toResponse(Employee employee) {

        return new EmployeeResponse(
                employee.idEmployee(),
                employee.firstName(),
                employee.secondName(),
                employee.lastName(),
                employee.motherLastName(),
                employee.age(),
                employee.gender(),
                employee.birthDate(),
                employee.position(),
                employee.createdAt(),
                employee.active()
        );
    }
}