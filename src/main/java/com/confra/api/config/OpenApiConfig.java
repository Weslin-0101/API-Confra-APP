package com.confra.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .contact(new Contact()
                                .name("Time Suporte Integração a API")
                                .email("dev@sicoob.com.br")
                        )
                        .title("API Confra APP")
                        .version("V1 - 1.0.0")
                        .description("API Confra APP using SpringBoot 3")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                );
    }
}
