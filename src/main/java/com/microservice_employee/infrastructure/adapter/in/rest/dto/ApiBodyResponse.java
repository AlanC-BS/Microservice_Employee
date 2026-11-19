package com.microservice_employee.infrastructure.adapter.in.rest.dto;

import com.microservice_employee.infrastructure.adapter.in.rest.constant.DocumentationMessages;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Schema(description = DocumentationMessages.API_BODY_RESPONSE_DESC)
public record ApiBodyResponse<T>(

        @Schema(
                description = DocumentationMessages.SUCCESS_DESC,
                example = DocumentationMessages.SUCCESS_EXAMPLE
        )
        boolean success,

        @Schema(
                description = DocumentationMessages.HTTP_STATUS_DESC,
                example = DocumentationMessages.HTTP_STATUS_EXAMPLE
        )
        int status,

        @Schema(
                description = DocumentationMessages.RESPONSE_MESSAGE_DESC,
                example = DocumentationMessages.RESPONSE_MESSAGE_EXAMPLE
        )
        String message,

        @Schema(
                description = DocumentationMessages.RESPONSE_DATA_DESC
        )
        T data,

        @Schema(
                description = DocumentationMessages.RESPONSE_TIMESTAMP_DESC,
                example = DocumentationMessages.RESPONSE_TIMESTAMP_EXAMPLE,
                type = "string",
                format = "date-time"
        )
        Instant timestamp,

        @Schema(
                description = DocumentationMessages.REQUEST_PATH_DESC,
                example = DocumentationMessages.REQUEST_PATH_EXAMPLE
        )
        String path

) {

    public static <T> ApiBodyResponse<T> success(
            HttpStatus status,
            String message,
            T data,
            String path) {

        return new ApiBodyResponse<>(
                true,
                status.value(),
                message,
                data,
                Instant.now(),
                path
        );
    }
}