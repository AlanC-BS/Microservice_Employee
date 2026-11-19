package com.microservice_employee.infrastructure.config;

import com.microservice_employee.infrastructure.adapter.in.rest.constant.DocumentationMessages;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI employeeOpen() {

        return new OpenAPI()
                .info(new Info()
                        .title(DocumentationMessages.API_TITLE)
                        .description(DocumentationMessages.API_DESCRIPTION)
                        .version(DocumentationMessages.API_VERSION)
                );
    }
}