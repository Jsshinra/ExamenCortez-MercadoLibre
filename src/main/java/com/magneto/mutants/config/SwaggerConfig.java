package com.magneto.mutants.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApiPersonalizado() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mutant Detector API")
                        .version("v1")
                        .description("API para detección de mutantes y estadísticas"));
    }
}
