package com.hotelium.limbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Header Authorization"))
                .components(new Components().addSecuritySchemes("Authorization",
                        createAPIKeyScheme()))
                .info(new Info().title("Hotelium API - Booking Reservation Manager")
                        .description("Hotelium API - Booking Reservation Manager")
                        .version("1.1")
                        .license(new License().name("License of API")
                                .url("API license URL")));
    }
}
