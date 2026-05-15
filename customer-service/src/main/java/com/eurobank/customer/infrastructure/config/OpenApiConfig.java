package com.eurobank.customer.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig{

    @Bean
    public OpenAPI customServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Eurobank Customer Service API")
                    .description("Customer management servicefor EuroBank platform")
                        .version("v1")
                        .contact(new Contact()
                                .name("Eurobank Engineering Team")
                                .email("engineering@eurobank.local")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));
    }

}