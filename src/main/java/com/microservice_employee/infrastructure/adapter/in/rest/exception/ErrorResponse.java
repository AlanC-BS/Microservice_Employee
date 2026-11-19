package com.microservice_employee.infrastructure.adapter.in.rest.exception;

import com.microservice_employee.infrastructure.adapter.in.rest.constant.DocumentationMessages;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Schema(description = DocumentationMessages.ERROR_RESPONSE_DESC)
public record ErrorResponse(

        @Schema(
                description = DocumentationMessages.ERROR_TIMESTAMP_DESC,
                example = DocumentationMessages.ERROR_TIMESTAMP_EXAMPLE,
                type = DocumentationMessages.STRING_TEXT,
                format = DocumentationMessages.DATE_TIME_TEXT
        )
        Instant timestamp,

        @Schema(
                description = DocumentationMessages.HTTP_STATUS_DESC,
                example = DocumentationMessages.ERROR_STATUS_EXAMPLE
        )
        int status,

        @Schema(
                description = DocumentationMessages.ERROR_TYPE_DESC,
                example = DocumentationMessages.ERROR_TYPE_EXAMPLE
        )
        String error,

        @Schema(
                description = DocumentationMessages.ERROR_MESSAGE_DESC,
                example = DocumentationMessages.ERROR_MESSAGE_EXAMPLE
        )
        String message,

        @Schema(
                description = DocumentationMessages.REQUEST_PATH_DESC,
                example = DocumentationMessages.REQUEST_PATH_EXAMPLE
        )
        String path,

        @Schema(
                description = DocumentationMessages.ERROR_DETAILS_DESC,
                example = DocumentationMessages.ERROR_DETAILS_EXAMPLE
        )
        List<String> details

) {
}