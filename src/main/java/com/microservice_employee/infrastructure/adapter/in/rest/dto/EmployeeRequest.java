
package com.microservice_employee.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservice_employee.infrastructure.adapter.in.rest.constant.DocumentationMessages;
import com.microservice_employee.infrastructure.adapter.in.rest.constant.RequestMessages;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = DocumentationMessages.DESC_REQUEST_EMPLOYEE)
public record EmployeeRequest(

        @Schema(
                description = DocumentationMessages.FIRST_NAME_DESC,
                example = DocumentationMessages.FIRST_NAME_EXAMPLE,
                maxLength = 60,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = RequestMessages.FIRST_NAME_REQUIRED)
        @Size(max = 60)
        String firstName,

        @Schema(
                description = DocumentationMessages.SECOND_NAME_DESC,
                example = DocumentationMessages.SECOND_NAME_EXAMPLE,
                maxLength = 60,
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        @Size(max = 60)
        String secondName,

        @Schema(
                description = DocumentationMessages.LAST_NAME_DESC,
                example = DocumentationMessages.LAST_NAME_EXAMPLE,
                maxLength = 60,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = RequestMessages.LAST_NAME_REQUIRED)
        @Size(max = 60)
        String lastName,

        @Schema(
                description = DocumentationMessages.MOTHER_LAST_NAME_DESC,
                example = DocumentationMessages.MOTHER_LAST_NAME_EXAMPLE,
                maxLength = 60,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = RequestMessages.MOTHER_LAST_NAME_REQUIRED)
        @Size(max = 60)
        String motherLastName,

        @Schema(
                description = DocumentationMessages.AGE_DESC,
                example = DocumentationMessages.AGE_EXAMPLE,
                minimum = DocumentationMessages.AGE_MIN,
                maximum = DocumentationMessages.AGE_MAX,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = RequestMessages.AGE_REQUIRED)
        @Min(value = 0, message = RequestMessages.AGE_NOT_NEGATIVE)
        @Max(value = 150, message = RequestMessages.AGE_LIMIT)
        Integer age,

        @Schema(
                description = DocumentationMessages.GENDER_DESC,
                example = DocumentationMessages.GENDER_EXAMPLE,
                allowableValues = {DocumentationMessages.GENDER_F_VALUE, DocumentationMessages.GENDER_M_VALUE,
                        DocumentationMessages.GENDER_X_VALUE},
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = RequestMessages.GENDER_REQUIRED)
        @Pattern(
                regexp = RequestMessages.REGEX_GENDER,
                message = RequestMessages.GENDER_OPTIONS
        )
        String gender,

        @Schema(
                description = DocumentationMessages.BIRTH_DATE_DESC,
                example = DocumentationMessages.BIRTH_DATE_EXAMPLE,
                type = DocumentationMessages.STRING_TEXT,
                format = DocumentationMessages.DATE_TEXT,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = RequestMessages.BIRTH_DATE_REQUIRED)
        @Past(message = RequestMessages.BIRTH_DATE_RESTRICTION)
        @JsonFormat(pattern = RequestMessages.PATTERN_DATE)
        LocalDate birthDate,

        @Schema(
                description = DocumentationMessages.POSITION_DESC,
                example = DocumentationMessages.POSITION_EXAMPLE,
                maxLength = 100,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = RequestMessages.POSITION_REQUIRED)
        @Size(max = 100)
        String position,

        @Schema(
                description = DocumentationMessages.ACTIVE_DESC,
                example = DocumentationMessages.ACTIVE_EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = RequestMessages.STATUS_REQUIRED)
        Boolean active
) {
}
