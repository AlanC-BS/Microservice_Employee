
package com.microservice_employee.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservice_employee.infrastructure.adapter.in.rest.constant.DocumentationMessages;
import com.microservice_employee.infrastructure.adapter.in.rest.constant.ResponseMessages;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = DocumentationMessages.DESC_RESPONSE_EMPLOYEE)
public record EmployeeResponse(

        @Schema(
                description = DocumentationMessages.UNIQUE_ID_MESSAGE,
                example = DocumentationMessages.ONE_VALUE
        )
        Long id,

        @Schema(
                description = DocumentationMessages.FIRST_NAME_DESC,
                example = DocumentationMessages.FIRST_NAME_EXAMPLE
        )
        String firstName,

        @Schema(
                description = DocumentationMessages.SECOND_NAME_DESC,
                example = DocumentationMessages.SECOND_NAME_EXAMPLE
        )
        String secondName,

        @Schema(
                description = DocumentationMessages.LAST_NAME_DESC,
                example = DocumentationMessages.LAST_NAME_EXAMPLE
        )
        String lastName,

        @Schema(
                description = DocumentationMessages.MOTHER_LAST_NAME_DESC,
                example = DocumentationMessages.MOTHER_LAST_NAME_EXAMPLE
        )
        String motherLastName,

        @Schema(
                description = DocumentationMessages.AGE_DESC,
                example = DocumentationMessages.AGE_EXAMPLE
        )
        Integer age,

        @Schema(
                description = DocumentationMessages.GENDER_DESC,
                example = DocumentationMessages.GENDER_EXAMPLE,
                allowableValues = {DocumentationMessages.GENDER_F_VALUE, DocumentationMessages.GENDER_M_VALUE,
                        DocumentationMessages.GENDER_X_VALUE}
        )
        String gender,

        @Schema(
                description = DocumentationMessages.BIRTH_DATE_DESC,
                example = DocumentationMessages.BIRTH_DATE_EXAMPLE,
                type = DocumentationMessages.STRING_TEXT,
                format = DocumentationMessages.DATE_TEXT
        )
        @JsonFormat(pattern = ResponseMessages.PATTERN_DATE)
        LocalDate birthDate,

        @Schema(
                description = DocumentationMessages.POSITION_DESC,
                example = DocumentationMessages.POSITION_EXAMPLE
        )
        String position,

        @Schema(
                description = DocumentationMessages.CREATED_AT_DESC,
                example = DocumentationMessages.CREATED_AT_EXAMPLE,
                type = DocumentationMessages.STRING_TEXT
        )
        @JsonFormat(pattern = ResponseMessages.PATTERN_DATE_TIME)
        LocalDateTime createdAt,

        @Schema(
                description = DocumentationMessages.ACTIVE_DESC,
                example = DocumentationMessages.ACTIVE_EXAMPLE
        )
        Boolean active
) {
}
