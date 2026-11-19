package com.microservice_employee.infrastructure.adapter.in.rest.dto;

import com.microservice_employee.infrastructure.adapter.in.rest.constant.DocumentationMessages;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = DocumentationMessages.PAGE_RESPONSE_DESC)
public record PageResponse<T>(

        @Schema(
                description = DocumentationMessages.PAGE_CONTENT_DESC
        )
        List<T> content,

        @Schema(
                description = DocumentationMessages.CURRENT_PAGE_DESC,
                example = DocumentationMessages.CURRENT_PAGE_EXAMPLE
        )
        int page,

        @Schema(
                description = DocumentationMessages.PAGE_SIZE_DESC,
                example = DocumentationMessages.PAGE_SIZE_EXAMPLE
        )
        int size,

        @Schema(
                description = DocumentationMessages.TOTAL_ELEMENTS_DESC,
                example = DocumentationMessages.TOTAL_ELEMENTS_EXAMPLE
        )
        long totalElements,

        @Schema(
                description = DocumentationMessages.TOTAL_PAGES_DESC,
                example = DocumentationMessages.TOTAL_PAGES_EXAMPLE
        )
        int totalPages,

        @Schema(
                description = DocumentationMessages.IS_FIRST_PAGE_DESC,
                example = DocumentationMessages.IS_FIRST_PAGE_EXAMPLE
        )
        boolean first,

        @Schema(
                description = DocumentationMessages.IS_LAST_PAGE_DESC,
                example = DocumentationMessages.IS_LAST_PAGE_EXAMPLE
        )
        boolean last

) {

    public static <T> PageResponse<T> from(Page<T> result) {
        return new PageResponse<>(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isFirst(),
                result.isLast()
        );
    }
}